package rs.raf.jun_Filip_Novakovic_882020RN.presentation.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import rs.raf.jun_Filip_Novakovic_882020RN.R
import rs.raf.jun_Filip_Novakovic_882020RN.databinding.ProfileBinding
import rs.raf.jun_Filip_Novakovic_882020RN.presentation.contract.MainContract
import rs.raf.jun_Filip_Novakovic_882020RN.presentation.view.state_user.UserState
import rs.raf.jun_Filip_Novakovic_882020RN.presentation.viewmodel.ViewModel
import timber.log.Timber

class ProfileFragment : Fragment(R.layout.profile) {

    private val thirdViewModel: MainContract.ViewModel3 by viewModel<ViewModel>()

    private var _binding: ProfileBinding? = null
    private val binding get() = _binding!!



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        initListeners()
        initUi()
    }


    private fun initListeners() {

        thirdViewModel.getByAccess("1")


        binding.logout.setOnClickListener{

                thirdViewModel.updateUserDecr()


            activity?.finish()
        }

    }

    private fun initUi() {

        thirdViewModel.userState.observe(viewLifecycleOwner) {
            //Timber.e("OVO JE PORUKA SA USERIMA " + it.toString())
            renderState(it)

        }




    }
    private fun renderState(state: UserState) {

        when (state) {

            is UserState.Success -> {

                for (item in state.user.iterator()){
                    Timber.e(item.toString())
                    Timber.e(item.logged)
                    if(item.logged.toInt() == 1){
                        //Timber.e("USAOOOOOOOOOO22")

                        binding.username.setText(item.username)
                        binding.password.setText(item.password)
                        binding.email.setText(item.email)

                        break

                    }
                }

            }
            is UserState.Error -> {

                Toast.makeText(context, state.message, Toast.LENGTH_SHORT).show()
            }
            is UserState.DataFetched -> {

                Toast.makeText(context, "User data fetch", Toast.LENGTH_LONG).show()
            }
            is UserState.Loading -> {

                Toast.makeText(context, "Logging user data", Toast.LENGTH_LONG).show()
            }
        }
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}