package ian.meda.ligaitesosoccer.ui.foro

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.parse.*
import ian.meda.ligaitesosoccer.R
import ian.meda.ligaitesosoccer.adapters.AdapterForo
import org.jetbrains.anko.doAsync
import java.time.Instant
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
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
            var newMessage: ParseObject = ParseObject("ForoMensajes")
            var pointer: ParseObject = ParseObject("User")
            var currDate: Date = Date()
            //newMessage.put("IDUser", pointer.objectId)
            newMessage.put("Mensaje", message)
            newMessage.put("Fecha", currDate)
            var test = newMessage.saveInBackground().isCompleted
            Log.v("ForoFragment", test.toString() + " completed")
        }

        return root
    }
}