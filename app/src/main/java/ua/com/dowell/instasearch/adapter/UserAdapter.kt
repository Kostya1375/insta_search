package ua.com.dowell.instasearch.adapter

import android.content.Intent
import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import ua.com.dowell.instasearch.R
import ua.com.dowell.instasearch.holder.UserHolder
import ua.com.dowell.instasearch.misc.CircleTransform
import ua.com.dowell.instasearch.model.pojo.User

/**
 * Created by kosty on 31.01.2018.
 */
class UserAdapter : RecyclerView.Adapter<UserHolder>() {

    private var list = emptyList<User>()

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): UserHolder? {
        val context = parent?.context ?: return null
        val inflater = LayoutInflater.from(context)
        return UserHolder(inflater.inflate(R.layout.item_user, parent, false))
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: UserHolder, position: Int) {
        val user = list[position]
        val context = holder.itemView.context
        val instagramUri = Intent(Intent.ACTION_VIEW, Uri.parse("http://instagram.com/${user.username}"))
        Picasso.with(holder.itemView?.context)
                .load(user.avatar)
                .transform(CircleTransform())
                .into(holder.avatar)
        holder.fullName.text = user.fullName
        holder.username.text = user.username
        holder.itemView.setOnClickListener { context.startActivity(instagramUri) }
    }

    fun setUsers(list: List<User>) {
        this.list = list
    }
}