package rs.raf.jun_Filip_Novakovic_882020RN.presentation.view.activities

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.koin.androidx.viewmodel.ext.android.viewModel
import rs.raf.jun_Filip_Novakovic_882020RN.databinding.SellBinding
import rs.raf.jun_Filip_Novakovic_882020RN.presentation.contract.MainContract
import rs.raf.jun_Filip_Novakovic_882020RN.presentation.view.sigleStockState.SingleStockState
import rs.raf.jun_Filip_Novakovic_882020RN.presentation.view.state_user.UserState
import rs.raf.jun_Filip_Novakovic_882020RN.presentation.view.state_user_stock.UserStockState
import rs.raf.jun_Filip_Novakovic_882020RN.presentation.viewmodel.StocksViewModel
import rs.raf.jun_Filip_Novakovic_882020RN.presentation.viewmodel.ViewModel
import timber.log.Timber

class SellActivity : AppCompatActivity() {
    private lateinit var binding: SellBinding
    private val thirdViewModel: MainContract.ViewModelStocks by viewModel<StocksViewModel>()
    private val secondaryViewModel: MainContract.ViewModel3 by viewModel<ViewModel>()

    var name: String = ""
    var symbol: String = ""
    var money: String = ""
    var userId: String = ""
    var last: String = ""
    var input: String = ""
    var ii: Int = 0
    companion object {
        private var toogle: String = ""
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SellBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init() {
        initUi()
    }

    private fun initUi() {
        //secondaryViewModel.getFetchedStockById("")

        secondaryViewModel.getByAccess2("1")
        thirdViewModel.getStockBySymbol("T")

        thirdViewModel.singleStockState.observe(this) {
            renderState(it)
        }

        if(Companion.toogle.equals("1") ){
            //Timber.e("USA")
            binding.input.visibility = View.INVISIBLE

        }else if(Companion.toogle.equals("") || Companion.toogle.equals("0")  ){
            //Timber.e("USA")
            binding.input.visibility = View.VISIBLE

        }

        binding.toggle.setOnClickListener{

            if(Companion.toogle.equals("0") || Companion.toogle.equals("")){
                Companion.toogle = "1"
                //Timber.e("AKTIVAN " + Companion.toogle)
            }else if(Companion.toogle.equals("1")){
                Companion.toogle = "0"
               // Timber.e("NEAKTIVAN " + Companion.toogle)
            }

            if(Companion.toogle.equals("1") ){
              //  Timber.e("USA")
                binding.input.visibility = View.INVISIBLE

            }else if(Companion.toogle.equals("") || Companion.toogle.equals("0")  ){
                //Timber.e("USA")
                binding.input.visibility = View.VISIBLE

            }

        }

        binding.sell.setOnClickListener{
            secondaryViewModel.getByAccess("1")
        }



        secondaryViewModel.userState.observe(this) {
            renderState2(it)
        }

        secondaryViewModel.stockState.observe(this){

           renderState3(it)
        }


    }
    private fun renderState3(state: UserStockState) {
        when (state) {
            is UserStockState.Success -> {

                Toast.makeText(this, "New user-stocks note data loaded", Toast.LENGTH_LONG).show()
            }
            is UserStockState.Error -> {

                Toast.makeText(this, state.message, Toast.LENGTH_SHORT).show()
            }
            is UserStockState.DataFetched -> {

                Toast.makeText(this, "fetched user-stocks data", Toast.LENGTH_LONG).show()


            }
            is UserStockState.Loading -> {

               // Timber.e("USAOO1234")
               // Timber.e("YES")
                for (item in state.stocks.iterator()) {
                    //Timber.e("Usao " + ii++)
                    if(Companion.toogle.equals("1")){
                      //  Timber.e("toogle 0")
                        input = binding.input.text.toString()
                       /* secondaryViewModel.update(
                            userId,
                            item.name,
                            (item.quantity.toInt() - input.toInt()).toString()
                        )*/
                        //delete
                        secondaryViewModel.decr((money.toFloat() + ( item.quantity.toFloat() * last.toFloat())).toString(),userId)
                        secondaryViewModel.delete(item.name,userId)

                    }else{
                        secondaryViewModel.decr((money.toFloat() + (item.quantity.toInt() * last.toFloat())).toString(),userId)
                        if (item.quantity.toInt() >= binding.input.text.toString().toInt()) {
                          //  Timber.e("USAOO12345")
                          //  Timber.e("toogle 1")
                            input = binding.input.text.toString()
                            secondaryViewModel.update(
                                userId,
                                item.name,
                                (item.quantity.toInt() - input.toInt()).toString()
                            )
                            secondaryViewModel.decr((money.toFloat() + (input.toFloat() * last.toFloat())).toString(),userId)

                        }
                    }

                }

                //}
            }
        }
    }

    private fun renderState(state: SingleStockState) {

        when (state) {

            is SingleStockState.Success -> {
               // Timber.e("STIGAO JE NOVI STOCK")
                name = state.new.name
                last = state.new.last
                //Timber.e("SYMBOL " + name )
                symbol = state.new.symbol
                binding.name.setText(name)
                binding.stockName.setText(name)


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
    private fun renderState2(state: UserState) {

        when (state) {

            is UserState.Success -> {

                for (item in state.user.iterator()){
                    Timber.e(item.toString())
                    Timber.e(item.logged)
                    if(item.logged.toInt() == 1){
                        //Timber.e("USAOO123")
//                        if(item.money.toInt() < )
                        userId = item.id
                        money = item.money
                        secondaryViewModel.getByIdAndName(item.id,name)

                        /*val temp: UUID = UUID.randomUUID()
                        val stockToAdd = StocksEntity(id = Long.toHexString(temp.mostSignificantBits)
                                + Long.toHexString(temp.leastSignificantBits),
                            name = name,userId= item.id)*/
                        /*secondaryViewModel.addStockToUser(stockToAdd)*/
                        /*binding.name.setText("LALALALAL")*/
                        break

                    }
                }

            }
            is UserState.Error -> {

                Toast.makeText(this, state.message, Toast.LENGTH_SHORT).show()
            }
            is UserState.DataFetched -> {

                for (item in state.userr.iterator()){
                    Timber.e(item.toString())
                    Timber.e(item.logged)
                    if(item.logged.toInt() == 1){
                        binding.stocks.setText(item.money)
                        break

                    }
                }



            }
            is UserState.Loading -> {

                Toast.makeText(this, "Logging data", Toast.LENGTH_LONG).show()
            }
        }
    }

}