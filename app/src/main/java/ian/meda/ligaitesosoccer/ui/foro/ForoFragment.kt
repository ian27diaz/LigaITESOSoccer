package ian.meda.ligaitesosoccer.ui.foro

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.parse.*
import ian.meda.ligaitesosoccer.R
import ian.meda.ligaitesosoccer.adapters.AdapterForo
import ian.meda.ligaitesosoccer.utils.SESSION_USER_ID
import ian.meda.ligaitesosoccer.utils.SHARED_PREFERENCES
import org.jetbrains.anko.doAsync
import java.util.*

class ForoFragment : Fragment() {
    private lateinit var sendMessageBtn : Button
    private lateinit var mMessage : EditText
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_foro, container, false)

        sendMessageBtn = root.findViewById(R.id.fragment_foro_sendmessage_button)
        mMessage = root.findViewById(R.id.fragment_foro_message_input)

        doAsync{
            val mensajes = ParseQuery.getQuery<ParseObject>("ForoMensajes")
            mensajes.include("IDUser")
            mensajes.findInBackground(object: FindCallback<ParseObject> {
                //val mensajesList :ArrayList<ParseObject> = arrayListOf<ParseObject>()
                val recyclerView = root.findViewById<RecyclerView>(R.id.fragment_foro_recyclerview)

                override fun done(mensajes: List<ParseObject>, e3: ParseException?) {
                    if(e3 == null){
                        Log.v("ForoFragment", mensajes.size.toString())
                        activity?.runOnUiThread {
                            recyclerView.adapter = AdapterForo(mensajes)
                            recyclerView.adapter?.notifyDataSetChanged()
                            recyclerView.layoutManager = LinearLayoutManager(context)
                        }
                    }
                }
            })
        }

        sendMessageBtn.setOnClickListener{
            val message = mMessage.text.toString()
            mMessage.setText("")
            val newMessage = ParseObject("ForoMensajes")
            val currDate = Date()
            val sharedPreferences = context!!.getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE)
            val currentUserId = sharedPreferences.getString(SESSION_USER_ID, "")
            newMessage.put("IDUser", ParseObject.createWithoutData("_User", currentUserId))
            newMessage.put("Mensaje", message)
            newMessage.put("Fecha", currDate)
            val test = newMessage.saveInBackground().isCompleted
            Log.v("ForoFragment", "$test completed")

            val builder = NotificationCompat.Builder(context!!, "AndroidCourse")
                .setSmallIcon(R.drawable.gol)
                .setContentTitle("Mensaje")
                .setContentText("Tu mensaje ha sido enviado con exito")
                .setStyle(NotificationCompat.BigTextStyle())
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)

            NotificationManagerCompat.from(context!!).notify(11, builder.build())
        }

        return root
    }
}