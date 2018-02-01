package ua.com.dowell.instasearch.holder

import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.item_user.view.*

/**
 * Created by kosty on 31.01.2018.
 */
class UserHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    val avatar = itemView.avatar
    val fullName = itemView.full_name
    val username = itemView.username
}