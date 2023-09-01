package rs.raf.jun_Filip_Novakovic_882020RN.presentation.view.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.koin.androidx.viewmodel.ext.android.viewModel
import rs.raf.jun_Filip_Novakovic_882020RN.databinding.StockDetailBinding
import rs.raf.jun_Filip_Novakovic_882020RN.presentation.contract.MainContract
import rs.raf.jun_Filip_Novakovic_882020RN.presentation.view.sigleStockState.SingleStockState
import rs.raf.jun_Filip_Novakovic_882020RN.presentation.view.state_user.UserState
import rs.raf.jun_Filip_Novakovic_882020RN.presentation.view.state_user_stock.UserStockState
import rs.raf.jun_Filip_Novakovic_882020RN.presentation.viewmodel.StocksViewModel
import rs.raf.jun_Filip_Novakovic_882020RN.presentation.viewmodel.ViewModel
import timber.log.Timber

class StockDetail : AppCompatActivity() {

    private lateinit var binding: StockDetailBinding
    private val thirdViewModel: MainContract.ViewModelStocks by viewModel<StocksViewModel>()
    private val secondaryViewModel: MainContract.ViewModel3 by viewModel<ViewModel>()
    var name: String = ""
    var symbol: String = ""
    var money: String = ""
    var toogle: String = ""
    var userId: String = ""
    var value: String = ""
    var br: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = StockDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init() {
        initUi()
    }

    private fun initUi() {

        thirdViewModel.getStockBySymbol("T")

        secondaryViewModel.getByAccess("1")

        secondaryViewModel.userState.observe(this) {

            renderState3(it)
        }

        thirdViewModel.singleStockState.observe(this) {
            renderState(it)
        }

        secondaryViewModel.stockState.observe(this){

            renderState2(it)
        }

        binding.buyBtn.setOnClickListener{
            val intent = Intent(this, BuyActiviry::class.java)
            startActivity(intent)
        }

        binding.sellBtn.setOnClickListener{
            val intent = Intent(this, SellActivity::class.java)
            startActivity(intent)
        }



    }

    private fun renderState3(state: UserState) {

        when (state) {

            is UserState.Success -> {


                for (item in state.user.iterator()){
                    Timber.e(item.toString())
                    Timber.e(item.logged)
                    if(item.logged.toInt() == 1){
                        userId = item.id

                        break

                    }
                }

            }
            is UserState.Error -> {

                Toast.makeText(this, state.message, Toast.LENGTH_SHORT).show()
            }
            is UserState.DataFetched -> {
                Toast.makeText(this, " USER STATE FETCH DATA BUY ACTIVITY", Toast.LENGTH_LONG).show()

            }
            is UserState.Loading -> {

                Toast.makeText(this, "LOADING USER STATE DATA BUY ACTIVITY", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun renderState2(state: UserStockState) {
        when (state) {
            is UserStockState.Success -> {

                Toast.makeText(this, "USER STOCK DATA SUCCES BUY ACTIVITY", Toast.LENGTH_LONG).show()
            }
            is UserStockState.Error -> {

                Toast.makeText(this, state.message, Toast.LENGTH_SHORT).show()
            }
            is UserStockState.DataFetched -> {

                Toast.makeText(this, "USER STOCK DATA FETCH BUY ACTIVITY ", Toast.LENGTH_LONG).show()

            }
            is UserStockState.Loading -> {


                if(state.stocks.isEmpty()){
                    toogle = "0"
                }else{
                    toogle = "1"
                }

                if(toogle.equals("0")){
                    binding.sellBtn.visibility = View.INVISIBLE
                }else{
                    binding.sellBtn.visibility = View.VISIBLE
                }

            }
        }
    }

    private fun renderState(state: SingleStockState) {

        when (state) {

            is SingleStockState.Success -> {

               binding.value.setText(state.new.last)
               binding.name.setText(state.new.name)
               binding.symbol.setText(state.new.symbol)
               binding.open.setText(state.new.open)
               binding.bid.setText(state.new.bid)
               binding.close.setText(state.new.close)
               binding.ask.setText(state.new.ask)
               /*binding.yield.setText(state.new.)*/
               /*binding.pe.setText(state.new.p)*/
               binding.cap.setText(state.new.metrics.marketCup)
               binding.eps.setText(state.new.metrics.eps)
               binding.ebit.setText(state.new.metrics.ebit)
               binding.beta.setText(state.new.metrics.beta)

                secondaryViewModel.getByIdAndName(userId,state.new.name)


            }
            is SingleStockState.Error -> {

                Toast.makeText(this, state.message, Toast.LENGTH_SHORT).show()
            }
            is SingleStockState.DataFetched -> {

                Toast.makeText(this, "Data fetch", Toast.LENGTH_LONG).show()
            }
            is SingleStockState.Loading -> {

                Toast.makeText(this, "Logging data", Toast.LENGTH_LONG).show()
            }
        }
    }

}