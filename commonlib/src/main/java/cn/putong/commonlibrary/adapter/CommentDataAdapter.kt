package cn.putong.commonlibrary.adapter

import android.content.Context
import android.net.Uri
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import cn.putong.commonlibrary.R
import cn.putong.commonlibrary.base.BaseRecyclerAdapter
import cn.putong.commonlibrary.helper.FrescoHelper
import cn.putong.commonlibrary.helper.TimeHelper
import cn.putong.commonlibrary.mvp.home.model.CommentModel
import cn.putong.commonlibrary.realm.AppDB
import com.facebook.drawee.view.SimpleDraweeView
import org.jetbrains.anko.textColor

/**
 * Comment类型数据适配器
 * Created by lala on 2018/1/8.
 */
class CommentDataAdapter(
        private var mList: ArrayList<CommentModel.Comment> = ArrayList(),
        private val onPicClickListener: (ArrayList<String>) -> Unit,
        private val onPositiveClickListener: (CommentModel.Comment) -> Unit,
        private val onNegativeClickListener: (CommentModel.Comment) -> Unit,
        private val onCommentClickListener: () -> Unit,
        private val onMoreClickListener: (View, CommentModel.Comment) -> Unit)
    : BaseRecyclerAdapter() {

    private val PAYLOAD = "payload"

    private var FOOTER: CommentModel.Comment = CommentModel.Comment()

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        val attachToRoot = false
        return if (viewType == TYPE_NORMAL)
            CardViewHolder(LayoutInflater.from(parent?.context)
                    .inflate(R.layout.item_comment, parent, attachToRoot))
        else
            PostDataAdapter
                    .FooterViewHolder(LayoutInflater.from(parent?.context)
                            .inflate(R.layout.item_recyclerview_footer, parent, attachToRoot))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?,
                                  position: Int, payloads: MutableList<Any>?) {

        if (payloads!!.isEmpty()) {
            onBindViewHolder(holder, position)
        } else
            if (holder is CardViewHolder) {
                val context = holder.negative_count.context
                val mComment = mList[position]

                holder.positive_count.textColor =
                        if (mComment.positive_status)
                            ContextCompat.getColor(context,
                                    R.color.comment_item_content_select_positive)
                        else
                            ContextCompat.getColor(context, R.color.textview_color)

                holder.negative_count.textColor =
                        if (mComment.negative_status)
                            ContextCompat.getColor(context,
                                    R.color.comment_item_content_select_negative)
                        else
                            ContextCompat.getColor(context, R.color.textview_color)

                holder.positive_count.text = context.resources.getString(R.string.positive_symbol, mComment.vote_positive)
                holder.negative_count.text = context.resources.getString(R.string.negative_symbol, mComment.vote_negative)
            }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        if (holder is CardViewHolder) {

            val context: Context = holder.author.context
            val mComment = mList[position]

            holder.author.text = mComment.comment_author
            holder.time.text = TimeHelper.format(TimeHelper.getDate(mComment.comment_date))
            holder.content.text = mComment.text_content.trim()
            holder.content.visibility =
                    if (mComment.text_content.trim().isEmpty())
                        View.GONE
                    else
                        View.VISIBLE

            if (mComment.pics.isEmpty()) {
                // 是段子数据,隐藏图片显示
                holder.pic.visibility = View.GONE
            } else {
                val mPicPath = mComment.pics[0]
                FrescoHelper
                        .setAnimatorController(Uri.parse(mPicPath), holder.pic)
                // 设置标识
                holder.pic.visibility = View.VISIBLE
                holder.pic.setOnClickListener {
                    onPicClickListener.invoke(mComment.pics)
                }
            }

            holder.positive_count.textColor =
                    if (mComment.positive_status)
                        ContextCompat.getColor(context,
                                R.color.comment_item_content_select_positive)
                    else
                        ContextCompat.getColor(context, R.color.textview_color)

            holder.negative_count.textColor =
                    if (mComment.negative_status)
                        ContextCompat.getColor(context,
                                R.color.comment_item_content_select_negative)
                    else
                        ContextCompat.getColor(context, R.color.textview_color)

            holder.positive_count.text = context.resources.getString(R.string.positive_symbol, mComment.vote_positive)
            holder.negative_count.text = context.resources.getString(R.string.negative_symbol, mComment.vote_negative)
            holder.comment_count.text = context.resources.getString(R.string.tucao_count_text, mComment.sub_comment_count)

            holder.positive_count.setOnClickListener {
                if (!mComment.positive_status && !mComment.negative_status) {
                    AppDB.savePositiveRecord(mComment.comment_ID)
                    mComment.vote_positive += 1
                    mComment.positive_status = true
                    notifyItemChanged(position, PAYLOAD)
                    onPositiveClickListener.invoke(mComment)
                }
            }

            holder.negative_count.setOnClickListener {
                if (!mComment.positive_status && !mComment.negative_status) {
                    AppDB.saveNegativeRecord(mComment.comment_ID)
                    mComment.vote_negative += 1
                    mComment.negative_status = true
                    notifyItemChanged(position, PAYLOAD)
                    onNegativeClickListener.invoke(mComment)
                }
            }

            holder.comment_count.setOnClickListener {
                onCommentClickListener.invoke()
            }

            holder.more.setOnClickListener {
                onMoreClickListener.invoke(holder.more, mComment)
            }

        }
    }

    override fun getItemCount() = mList.size

    override fun getItemViewType(position: Int): Int {
        return if (mList[position].comment_ID.isEmpty())
            TYPE_FOOTER
        else
            TYPE_NORMAL
    }

    fun updateList(mList: ArrayList<CommentModel.Comment>) {
        mList.setPosAndNegaStatus()
        this.mList = mList
        notifyDataSetChanged()
    }

    /**
     * 设置[点赞、讨厌]状态
     */
    private fun ArrayList<CommentModel.Comment>.setPosAndNegaStatus() {
        for (mComment in this) {
            if (AppDB.getPositiveRecord(mComment.comment_ID) != null) {
                mComment.vote_positive += 1
                mComment.positive_status = true
            }

            if (AppDB.getNegativeRecord(mComment.comment_ID) != null) {
                mComment.vote_negative += 1
                mComment.negative_status = true
            }
        }
    }

    override fun addFooter() {
        mList.add(FOOTER)
        notifyItemInserted(mList.size - 1)
    }

    override fun removeFooter() {
        mList.removeAt(mList.size - 1)
        notifyItemRemoved(mList.size)
    }

    class CardViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val author: TextView = view.findViewById(R.id.author)
        val time: TextView = view.findViewById(R.id.time)
        val content: TextView = view.findViewById(R.id.content)
        val pic_content: View = view.findViewById(R.id.pic_content)
        val pic: SimpleDraweeView = view.findViewById(R.id.pic)
        val positive_count: TextView = view.findViewById(R.id.positive_count)
        val negative_count: TextView = view.findViewById(R.id.negative_count)
        val comment_count: TextView = view.findViewById(R.id.comment_count)
        val more: ImageView = view.findViewById(R.id.more)
    }

    class FooterViewHolder(view: View) : RecyclerView.ViewHolder(view)

}