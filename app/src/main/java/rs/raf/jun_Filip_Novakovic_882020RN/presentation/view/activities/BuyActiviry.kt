package rs.raf.jun_Filip_Novakovic_882020RN.presentation.view.activities

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.koin.androidx.viewmodel.ext.android.viewModel
import rs.raf.jun_Filip_Novakovic_882020RN.data.model_stocks.StocksEntity
import rs.raf.jun_Filip_Novakovic_882020RN.databinding.BuyBinding
import rs.raf.jun_Filip_Novakovic_882020RN.presentation.contract.MainContract
import rs.raf.jun_Filip_Novakovic_882020RN.presentation.view.sigleStockState.SingleStockState
import rs.raf.jun_Filip_Novakovic_882020RN.presentation.view.state_user.UserState
import rs.raf.jun_Filip_Novakovic_882020RN.presentation.view.state_user_stock.UserStockState
import rs.raf.jun_Filip_Novakovic_882020RN.presentation.viewmodel.StocksViewModel
import rs.raf.jun_Filip_Novakovic_882020RN.presentation.viewmodel.ViewModel
import timber.log.Timber
import java.lang.Long
import java.util.*

class BuyActiviry : AppCompatActivity() {

    private lateinit var binding: BuyBinding
    private val thirdViewModel: MainContract.ViewModelStocks by viewModel<StocksViewModel>()
    private val secondaryViewModel: MainContract.ViewModel3 by viewModel<ViewModel>()

    var name: String = ""
    var symbol: String = ""
    var money: String = ""
    var toogle: String = ""
    var userId: String = ""
    var value: String = ""
    var br: Int = 0

    companion object {
        private var done: Int = 0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = BuyBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init() {
        initUi()
    }

    private fun initUi() {
        //UZIMAMO USERA DA BI NASETOVALI MONEY NA POCETKU EKRANA
        secondaryViewModel.getByAccess2("1")
        //UZIMAMO STOCK ITEM IZ LISTE I SETUJE PODATKE U VARIABLE KOJE SU NAM POTREBNE DALJE
        thirdViewModel.getStockBySymbol("T")

        //OBSERVER KOJI SE DESI KADA STIGLNU PODACI IZ LISTE STOCKOVA
        thirdViewModel.singleStockState.observe(this) {
            Timber.e("OSLUSKUJEMO NA SINGLE STOCK STATE")
            renderState(it)
        }


        //OVDE NA KLIK BUY DOVLACIMO USERA KOJI JE LOGOVAN
        binding.buy.setOnClickListener{
            Timber.e("DUGME CLICK")
            secondaryViewModel.getByAccess("1")
            //done = 0

        }

        binding.toggle.setOnClickListener{

                if(binding.toggle.isActivated){
                    toogle = "1"
                }else{
                    toogle = "0"
                }

        }

        //OBSERVER KOJI SE AKTIVIRA KADA STIGLU PODACI O USERU I ONDA TU RADMO NESTO
        secondaryViewModel.userState.observe(this) {
            Timber.e("OSLUSKUJEMO NA USER STATE")
            renderState2(it)
        }

        secondaryViewModel.stockState.observe(this){
            Timber.e("OSLUSKUJEMO NA USER STATE STOCK")
            renderState3(it)
        }


    }
    private fun renderState3(state: UserStockState) {
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

                //OVDE STIZEMO KADA KLIKNEMO DUGME BUY ODNOSNO NAKON STO DOVECMO STOCK SA IDEM USERA I IMENOM ZADATIM DOLE
                //NAKON TOGA GLEDAMO DA LI POSTOJI UOPSTE STOCK SA TIM USERiD-JEM I NAME-OM AKO NE ONDA GA PRVI PUT [PRAVIMO I STAVLJAMO
                //AKO POSTOJI ONDA SAMO RADIMO UPDATE TJ DODAJEMO QTY +1 A ODUZIMAMO PARE KORISNIKU
                Timber.e("USAO U USER STOCK SATE LOADING")
                if(state.stocks.isEmpty()){
                    Timber.e("USAO U USER STATE LOADING I POSTOJI STOCK SA TIM IMENOM")

                    if(money.toFloat() >= value.toFloat()){
                        val temp: UUID = UUID.randomUUID()
                        val stockToAdd = StocksEntity(id = Long.toHexString(temp.mostSignificantBits)
                                + Long.toHexString(temp.leastSignificantBits),
                            name = name,userId= userId,"1",symbol,value)
                        secondaryViewModel.addStockToUser(stockToAdd)
                        secondaryViewModel.decr((money.toFloat() - value.toFloat()).toString(),userId)

                    }
                    /*secondaryViewModel.addStockToUser(stockToAdd)*/
                }else{
                    Timber.e("USAO U USER STATE LOADING I NE POSTOJI STOCK SA TIM IMENOM")
                     for (item in state.stocks.iterator()){
                         Timber.e("USAO U USER STATE LOADING I PROLAZIMO KROZ LISTU STOKOVA")
                    Timber.e("broj " + br++ )

                    secondaryViewModel.update(userId,item.name,(item.quantity.toInt()+1).toString())
                         secondaryViewModel.decr((money.toFloat() - value.toFloat()).toString(),userId)
                         break;
                    }
                   return

                }
                return

            }
        }
    }

    private fun renderState(state: SingleStockState) {

        when (state) {

            is SingleStockState.Success -> {
                Timber.e("USAO U SINGLE STATE STOCK SUCCES I SADA SETUJEMO PODATKE IZ STOCKA U VARIABLE")

                name = state.new.name
                symbol = state.new.symbol
                binding.name.setText(name)
                binding.stockvalue.setText(symbol)
                value = state.new.last


            }
            is SingleStockState.Error -> {

                Toast.makeText(this, state.message, Toast.LENGTH_SHORT).show()
            }
            is SingleStockState.DataFetched -> {

                Toast.makeText(this, "SINGLE STOCK DATA FETCH BUY ACTIVITY", Toast.LENGTH_LONG).show()
            }
            is SingleStockState.Loading -> {

                Toast.makeText(this, "LOADING SINGLE STOCK DATA BUY ACTIVITY", Toast.LENGTH_LONG).show()
            }
        }
    }
    private fun renderState2(state: UserState) {

        when (state) {

            is UserState.Success -> {

                //OVO SE AKTIVIRA KADA URADIMO GETACCESS
                Timber.e("USAO U USER STATE SUCCES I OVDE SETUJEMO PODATKE IZ USERA U VARIABLE")
                for (item in state.user.iterator()){
                    Timber.e(item.toString())
                    Timber.e(item.logged)
                    if(item.logged.toInt() == 1){
                        userId = item.id
                        money = item.money

                        //UZIMAMO STOCK SA ODREDJENIM IMENOM ZA TOG USERA
                        secondaryViewModel.getByIdAndName(item.id,name)

                        break

                    }
                }

            }
            is UserState.Error -> {

                Toast.makeText(this, state.message, Toast.LENGTH_SHORT).show()
            }
            is UserState.DataFetched -> {
                //OVO SE AKTIVIRA KADA URADIMO GETACCESS2 I OVDE SETUJEMO MONEY NA EKRANU
                Timber.e("USAO U USER STATE DATA FETCH I SETUJEMO PODATKE NA EKRANU OVDE")
                if(done == 0) {
                    for (item in state.userr.iterator()) {
                        Timber.e(item.toString())
                        Timber.e(item.logged)
                        if (item.logged.toInt() == 1) {
                            Timber.e("USAO U USER STATE DATA FETCH I PROLAZIMO KROZ LISTU")
                            binding.balance.setText(item.money)
                            done = 1
                            break

                        }
                    }
                }

            }
            is UserState.Loading -> {

                Toast.makeText(this, "LOADING USER STATE DATA BUY ACTIVITY", Toast.LENGTH_LONG).show()
            }
        }
    }

}