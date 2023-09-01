package rs.raf.jun_Filip_Novakovic_882020RN.presentation.view.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.koin.androidx.viewmodel.ext.android.viewModel
import rs.raf.jun_Filip_Novakovic_882020RN.databinding.LoginBinding
import rs.raf.jun_Filip_Novakovic_882020RN.presentation.contract.MainContract
import rs.raf.jun_Filip_Novakovic_882020RN.presentation.view.state_user.UserState
import rs.raf.jun_Filip_Novakovic_882020RN.presentation.viewmodel.ViewModel
import timber.log.Timber

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: LoginBinding
    private val thirdViewModel: MainContract.ViewModel3 by viewModel<ViewModel>()

    @Override
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



      /* val splashScreen : SplashScreen = installSplashScreen()


         splashScreen.setKeepVisibleCondition() {
             thirdViewModel.getAllUsers()

             Thread.sleep(3000)
             false
         }*/
        binding = LoginBinding.inflate(layoutInflater)
        setContentView(binding.root)



        init()
    }

    private fun init() {
        initUi()
    }

    private fun initUi() {


        binding.button.setOnClickListener{
            val usernamee: String = binding.userInput.text.toString()
          //  Timber.e("USAO U LOGIN")
             thirdViewModel.getUserByName(usernamee)
            /*val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)*/
        }
        thirdViewModel.userState.observe(this) {
            //Timber.e("OVO JE PORUKA SA USERIMA " + it.toString())
            renderState(it)

        }




    }
    private fun renderState(state: UserState) {
        var i: Int = 0;
        when (state) {

            is UserState.Success -> {

                val username: String = binding.userInput.text.toString()
                val password: String = binding.editTextTextPassword.text.toString()


                //Timber.e("USAOOOOOOOOOO")
                for (item in state.user.iterator()){
                    Timber.e(item.toString())
                    Timber.e(item.logged)
                    if(item.logged.toInt() == 1){
                      //  Timber.e("USAOOOOOOOOOO22")
                        i = 1
                        break
                    }
                }
                if(i == 1){
                    i = 2;
                    Toast.makeText(applicationContext, "You are already logged in", Toast.LENGTH_LONG).show()
                    val intent = Intent (this, MainActivity::class.java)
                    startActivity(intent)

                }

            }
            is UserState.Error -> {

                Toast.makeText(applicationContext, state.message, Toast.LENGTH_SHORT).show()
            }
            is UserState.DataFetched -> {

               /* Toast.makeText(applicationContext, "Fresh data fetched from the server", Toast.LENGTH_LONG).show()*/
                val username: String = binding.userInput.text.toString()
                val email: String = binding.email.text.toString()
                val password: String = binding.editTextTextPassword.text.toString()
                var set = 0

                if(!username.equals("")) {

                    for (item in state.userr.iterator()) {
                        if (item.username.equals(username) && item.password.equals(password) && item.email.equals(email)) {

                            set = 1
                            break
                        }
                    }
                    if(set == 1) {
                        binding.userInput.setText("")
                        binding.editTextTextPassword.setText("")
                        binding.email.setText("")
                        Toast.makeText(applicationContext, "Logged in", Toast.LENGTH_LONG).show()
                        for (item in state.userr.iterator()) {
                            if (item.username.equals(username) && item.password.equals(password)) {
                                thirdViewModel.updateUser(username)
                                val intent = Intent(this, MainActivity::class.java)
                                startActivity(intent)
                                break;
                            }
                        }
                    }else{
                        Toast.makeText(applicationContext,"User not found", Toast.LENGTH_LONG).show()
                    }
                }




            }
            is UserState.Loading -> {

                Toast.makeText(applicationContext, "Logging data", Toast.LENGTH_LONG).show()
            }
        }
    }


}