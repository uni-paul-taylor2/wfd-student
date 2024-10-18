package dev.kwasi.echoservercomplete.adapters

import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import dev.kwasi.echoservercomplete.R
import dev.kwasi.echoservercomplete.models.ContentModel

class ChatListAdapter(isStudent: Boolean) : RecyclerView.Adapter<ChatListAdapter.ViewHolder>(){
    private val chatList:MutableList<ContentModel> = mutableListOf()
    private val student:Boolean = isStudent

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val messageView: TextView = itemView.findViewById(R.id.messageTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.chat_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val chat = chatList[position]
        if(student) {
            (holder.messageView.parent as RelativeLayout).gravity =
                if (chat.student_id == "192.168.49.1") Gravity.START else Gravity.END;
        }
        else {
            (holder.messageView.parent as RelativeLayout).gravity =
                if (chat.student_id == "192.168.49.1") Gravity.END else Gravity.START;
        }
        val message: String = chat.message
        val studentID: String = chat.student_id
        holder.messageView.text = "$studentID: $message"
    }

    override fun getItemCount(): Int {
        return chatList.size
    }

    fun addItemToEnd(contentModel: ContentModel){
        chatList.add(contentModel)
        notifyItemInserted(chatList.size)
    }
}