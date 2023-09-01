package rs.raf.jun_Filip_Novakovic_882020RN.presentation.view.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import rs.raf.jun_Filip_Novakovic_882020RN.R
import rs.raf.jun_Filip_Novakovic_882020RN.databinding.PortfolioListBinding
import rs.raf.jun_Filip_Novakovic_882020RN.presentation.contract.MainContract
import rs.raf.jun_Filip_Novakovic_882020RN.presentation.view.activities.StockDetail
import rs.raf.jun_Filip_Novakovic_882020RN.presentation.view.state_user_stock.UserStockState
import rs.raf.jun_Filip_Novakovic_882020RN.presentation.view.recycler.adapter.UserStockAdapter
import rs.raf.jun_Filip_Novakovic_882020RN.presentation.view.state_user.UserState
import rs.raf.jun_Filip_Novakovic_882020RN.presentation.viewmodel.ViewModel
import timber.log.Timber


class PortfolioFragment : Fragment(R.layout.portfolio_list) {

    private val secondaryViewModel: MainContract.ViewModel3 by sharedViewModel<ViewModel>()
    private var _binding: PortfolioListBinding? = null
    private val binding get() = _binding!!


    private lateinit var adapter: UserStockAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = PortfolioListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        initUi()
  /*      initObservers()*/


    }


    private fun initUi() {
        initRecycler()
        initListeners()
    }

    private fun initRecycler() {
        binding.listRv.layoutManager = LinearLayoutManager(context)
        adapter = UserStockAdapter()
        binding.listRv.adapter = adapter
    }

    private fun initListeners() {
                 secondaryViewModel.getByAccess("1")

                binding.click.setOnClickListener{
                    val intent = Intent(this.context, StockDetail::class.java)
                    startActivity(intent)
                }

                secondaryViewModel.userState.observe(viewLifecycleOwner) {

                    renderState(it)

                }


                secondaryViewModel.stockState.observe(viewLifecycleOwner, Observer {

                    renderState2(it)
                })

    }


    private fun renderState(state: UserState) {

        when (state) {

            is UserState.Success -> {

                for (item in state.user.iterator()){
                   // Timber.e(item.toString())
                   // Timber.e(item.logged)
                    if(item.logged.toInt() == 1){
                        //Timber.e("USAOOOOOOOOOO22")
                        if(item.firstTime.equals("0") && item.money.equals("0")){
                            secondaryViewModel.portfolioUpdate("1")
                            binding.money.setText(item.money)
                            binding.portfolioValue.setText(item.portfolioValue)
                           // Timber.e("USAOOOOOOOOOO33")
                        }else{
                            binding.money.setText(item.money)
                            binding.portfolioValue.setText(item.portfolioValue)
                           // Timber.e("USAOOOOOOOOOO44")
                            secondaryViewModel.getById(item.id)

                        }


                        break

                    }
                }

            }
            is UserState.Error -> {

                Toast.makeText(context, state.message, Toast.LENGTH_SHORT).show()
            }
            is UserState.DataFetched -> {

                Toast.makeText(context, "Data fetch", Toast.LENGTH_LONG).show()
            }
            is UserState.Loading -> {

                Toast.makeText(context, "Logging data", Toast.LENGTH_LONG).show()
            }
        }
    }
    private fun renderState2(state: UserStockState) {
        when (state) {
            is UserStockState.Success -> {
                showLoadingState(false)
                adapter.submitList(state.stock)
                Toast.makeText(context, "New user-stocks note data loaded", Toast.LENGTH_LONG).show()
            }
            is UserStockState.Error -> {
                showLoadingState(false)
                Toast.makeText(context, state.message, Toast.LENGTH_SHORT).show()
            }
            is UserStockState.DataFetched -> {
                showLoadingState(false)
                Toast.makeText(context, "Fresh user-stocks data fetched from the BASE", Toast.LENGTH_LONG).show()
            }
            is UserStockState.Loading -> {
                showLoadingState(true)
                Toast.makeText(context, "Logging user-stocks data", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun showLoadingState(loading: Boolean) {
        binding.listRv.isVisible = !loading
        binding.loadingPb.isVisible = loading
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private var i: Int = 0
    }
}