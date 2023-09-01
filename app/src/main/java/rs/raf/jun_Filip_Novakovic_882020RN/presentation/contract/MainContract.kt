package rs.raf.jun_Filip_Novakovic_882020RN.presentation.contract

import androidx.lifecycle.LiveData
import rs.raf.jun_Filip_Novakovic_882020RN.data.datasources.model_user.User
import rs.raf.jun_Filip_Novakovic_882020RN.data.model_stocks.StocksEntity
import rs.raf.jun_Filip_Novakovic_882020RN.presentation.view.state_user_stock.AddUserStocksState
import rs.raf.jun_Filip_Novakovic_882020RN.presentation.view.state_user_stock.UserStockState
import rs.raf.jun_Filip_Novakovic_882020RN.presentation.view.sigleStockState.AddSingleStockState
import rs.raf.jun_Filip_Novakovic_882020RN.presentation.view.sigleStockState.SingleStockState
import rs.raf.jun_Filip_Novakovic_882020RN.presentation.view.state_news.AddNewsState
import rs.raf.jun_Filip_Novakovic_882020RN.presentation.view.state_news.NewsState
import rs.raf.jun_Filip_Novakovic_882020RN.presentation.view.state_user.AddUserState
import rs.raf.jun_Filip_Novakovic_882020RN.presentation.view.state_user.UserState
import rs.raf.jun_Filip_Novakovic_882020RN.presentation.view.state_stocks.AddStocksState
import rs.raf.jun_Filip_Novakovic_882020RN.presentation.view.state_stocks.StockState

interface MainContract {



    interface ViewModel3 {


        val userState: LiveData<UserState>
        val addUserDone: LiveData<AddUserState>
        val stockState: LiveData<UserStockState>
        val addDone1: LiveData<AddUserStocksState>
        val singleStockState: LiveData<SingleStockState>
        val addDone3: LiveData<AddSingleStockState>



        //fun fetchAllStocks()

        fun addStockToUser(stock: StocksEntity)
        fun getByIdAndName(id:String,name:String)
        fun update(id:String,name:String,qty:String)
        fun getAllUsers()
        fun updateUser(usr: String)
        fun updateUserDecr()
        fun decr(money:String,id:String)
        fun delete(name:String,id:String)
        fun getByAccess(access: String)
        fun getByAccess2(access: String)
        fun getById(id: String)
        fun portfolioUpdate(access:String)
        fun getUserByName(name: String)
        fun addUser(user: User)
    }


    interface ViewModelNews {
        val newsState: LiveData<NewsState>
        val addDone: LiveData<AddNewsState>

        fun fetchAllMovies()
    }

    interface ViewModelStocks {

        val stockState: LiveData<StockState>
        val addDone: LiveData<AddStocksState>
        val singleStockState: LiveData<SingleStockState>
        val addDone2: LiveData<AddSingleStockState>

        fun fetchAllMovies()
        fun getStockBySymbol(symbol:String)

    }




}