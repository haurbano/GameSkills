package innovappte.mobile.gamesskills.presentation.utils

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentActivity
import innovappte.mobile.gamesskills.R

class FiFaDialog: DialogFragment() {

    lateinit var buttonAction: () -> Unit
    private var titleResource = 0
    private var messageResource = 0
    private var buttonTextResource = 0

    companion object {
        object KEY {
            const val TITLE = "TITLE"
            const val MESSAGE = "MESSAGE"
            const val BUTTON_TEXT = "BUTTON_TEXT"
            const val FRAGMENT_TAG = "FIFA_DIALOG"
        }

        fun newInstance(title: Int, message: Int, buttonText: Int, buttonAction: () -> Unit): FiFaDialog {
            val dialog = FiFaDialog()
            dialog.arguments = Bundle().apply {
                putInt(KEY.TITLE, title)
                putInt(KEY.MESSAGE, message)
                putInt(KEY.BUTTON_TEXT, buttonText)
            }
            dialog.buttonAction = buttonAction
            return dialog
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        titleResource = arguments?.getInt(KEY.TITLE) ?: 0
        messageResource = arguments?.getInt(KEY.MESSAGE) ?: 0
        buttonTextResource = arguments?.getInt(KEY.BUTTON_TEXT) ?: 0
    }

    fun show(context: FragmentActivity) {
        val fragmentTransaction = context.supportFragmentManager.beginTransaction()
        val previousFragment = context.supportFragmentManager.findFragmentByTag(KEY.FRAGMENT_TAG)
        if (previousFragment != null) fragmentTransaction.remove(previousFragment)
        fragmentTransaction
                .addToBackStack(null)
                .add(this, KEY.FRAGMENT_TAG)
                .commit()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.dialog_fifa, container, false)
        dialog.window.setBackgroundDrawable(context?.getDrawable(R.drawable.bg_dialog_fifa))
        val txtTitle = view.findViewById<TextView>(R.id.txtTitle)
        txtTitle.text = getString(titleResource)

        val txtMessage = view.findViewById<TextView>(R.id.txtMessage)
        txtMessage.text = getString(messageResource)

        val btnMainAction = view.findViewById<Button>(R.id.btnMainAction)
        btnMainAction.text = getString(buttonTextResource)
        btnMainAction.setOnClickListener {
            this.dismiss()
            buttonAction()
        }
        return view
    }

    class Builder {
        private var title = R.string.title_fifa_dialog_default
        private var message = R.string.msg_fifa_dialog_default
        private var buttonText = R.string.btn_title_fifa_dialog_default_message
        private var buttonAction = {}

        fun setTitle(title: Int): Builder{
            this.title = title
            return this
        }

        fun setMessage(message: Int): Builder {
            this.message = message
            return this
        }

        fun setButtonText(buttonText: Int): Builder {
            this.buttonText = buttonText
            return this
        }

        fun setButtonAction(buttonAction: () -> Unit): Builder {
            this.buttonAction = buttonAction
            return this
        }

        fun Build(): FiFaDialog {
            return newInstance(
                    title = title,
                    message = message,
                    buttonText = buttonText,
                    buttonAction = buttonAction
            )
        }

    }
}