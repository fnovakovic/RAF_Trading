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
import rs.raf.jun_Filip_Novakovic_882020RN.databinding.StockListBinding
import rs.raf.jun_Filip_Novakovic_882020RN.presentation.contract.MainContract
import rs.raf.jun_Filip_Novakovic_882020RN.presentation.view.activities.StockDetail
import rs.raf.jun_Filip_Novakovic_882020RN.presentation.view.recycler.adapter.StockAdapter
import rs.raf.jun_Filip_Novakovic_882020RN.presentation.view.state_stocks.StockState
import rs.raf.jun_Filip_Novakovic_882020RN.presentation.viewmodel.StocksViewModel

class StocksFragment : Fragment(R.layout.stock_list) {

    private val mainViewModel: MainContract.ViewModelStocks by sharedViewModel<StocksViewModel>()

    private var _binding: StockListBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: StockAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = StockListBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        initUi()
        initObservers()
    }

    private fun initUi() {
        initRecycler()
        initListeners()
    }

    private fun initRecycler() {
        binding.listRv.layoutManager = LinearLayoutManager(context)
        adapter = StockAdapter()
        binding.listRv.adapter = adapter
    }

    private fun initListeners() {
        binding.search.setOnClickListener{
            val intent = Intent(this.context, StockDetail::class.java)
            startActivity(intent)
        }
    }

    private fun initObservers() {

        mainViewModel.stockState.observe(viewLifecycleOwner, Observer {
            //Timber.e("OVO JE PORUKA " + it.toString())
            renderState(it)
        })

        mainViewModel.fetchAllMovies()


    }

    private fun renderState(state: StockState) {
        when (state) {
            is StockState.Success -> {
                showLoadingState(false)
                adapter.submitList(state.stock)
                Toast.makeText(context, "Stocks data from cache loaded", Toast.LENGTH_LONG).show()
            }
            is StockState.Error -> {
                showLoadingState(false)
                Toast.makeText(context, state.message, Toast.LENGTH_SHORT).show()
            }
            is StockState.DataFetched -> {
                showLoadingState(false)
                Toast.makeText(context, "Fresh stocks data fetched from the server", Toast.LENGTH_LONG).show()
            }
            is StockState.Loading -> {
                showLoadingState(true)
                Toast.makeText(context, "Logging stocks data", Toast.LENGTH_LONG).show()
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

}