package cn.putong.home.adapter

import android.net.Uri
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cn.putong.commonlibrary.base.BaseRecyclerAdapter
import cn.putong.commonlibrary.util.FrescoUtil
import cn.putong.commonlibrary.util.TimeUtil
import cn.putong.home.R
import cn.putong.home.mvp.data.model.CardModel
import com.facebook.drawee.view.SimpleDraweeView
import kotlinx.android.synthetic.main.view_card_item_content.view.*


/**
 * Card类型数据适配器
 * Created by lala on 2018/1/8.
 */
class CardDataAdapter(private var mList: ArrayList<CardModel.Comment> = ArrayList())
    : BaseRecyclerAdapter() {

    private var FOOTER: CardModel.Comment = CardModel.Comment()

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        val attachToRoot = false
        return if (viewType == TYPE_NORMAL)
            CardViewHolder(LayoutInflater.from(parent?.context).
                    inflate(R.layout.item_card, parent, attachToRoot))
        else
            NormalDataAdapter.FooterViewHolder(LayoutInflater.from(parent?.context).
                    inflate(R.layout.item_recyclerview_footer, parent, attachToRoot))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        if (holder is CardViewHolder)
            with(holder.itemView!!) {
                val mComment = mList[position]

                author.text = mComment.comment_author
                time.text = TimeUtil.format(TimeUtil.getDate(mComment.comment_date))
                content.text = mComment.text_content.trim()
                content.visibility = if (mComment.text_content.trim().isEmpty()) View.GONE else View.VISIBLE

                if (mComment.pics.isEmpty()) {
                    // 是段子数据,隐藏图片显示
                    picviewpager.visibility = View.GONE
                } else {
                    val mPicList = ArrayList<SimpleDraweeView>()
                    mComment.pics.forEach {
                        val mPicView = SimpleDraweeView(context)
                        FrescoUtil.setAnimatorController(Uri.parse(it), mPicView)
                        mPicList.add(mPicView)
                    }
                    picviewpager.adapter = PicAdapter(mPicList)
                    picviewpager.visibility = View.VISIBLE
                }

                positive_count.text = context.resources.getString(R.string.boringpic_content_positive_symbol) + mComment.vote_positive
                negative_count.text = context.resources.getString(R.string.boringpic_content_negative_symbol) + mComment.vote_positive
                comment_count.text = resources.getString(R.string.boringpic_content_comment_count_text) + mComment.sub_comment_count
            }
    }

    override fun getItemCount() = mList.size

    override fun getItemViewType(position: Int): Int {
        return if (mList[position].comment_ID.isEmpty())
            TYPE_FOOTER
        else
            TYPE_NORMAL
    }

    fun updateList(mList: ArrayList<CardModel.Comment>) {
        this.mList = mList
        notifyDataSetChanged()
    }

    override fun addFooter() {
        mList.add(FOOTER)
        notifyItemInserted(mList.size - 1)
    }

    override fun removeFooter() {
        mList.removeAt(mList.size - 1)
        notifyItemRemoved(mList.size)
    }

    class CardViewHolder(view: View) : RecyclerView.ViewHolder(view)

    class FooterViewHolder(view: View) : RecyclerView.ViewHolder(view)


    /**
     * 图片Pager适配器
     */
    class PicAdapter(private var mViewList: List<SimpleDraweeView> = listOf()) : PagerAdapter() {

        override fun isViewFromObject(view: View?, `object`: Any?) = view == `object`

        override fun getCount() = mViewList.size

        override fun instantiateItem(container: ViewGroup?, position: Int): Any {
            (container as ViewPager).addView(mViewList.get(position))
            return mViewList[position]
        }

        override fun destroyItem(container: ViewGroup?, position: Int, `object`: Any?) {
            (container as ViewPager).removeView(mViewList.get(position))
        }

        fun updatePicList(mViewList: List<SimpleDraweeView>) {
            this.mViewList = mViewList
            notifyDataSetChanged()
        }
    }
}