package rs.raf.jun_Filip_Novakovic_882020RN.data.repositories

import io.reactivex.Observable
import rs.raf.jun_Filip_Novakovic_882020RN.data.model_news.News

interface NewsRepository {
    fun fetchAll(): Observable<List<News>>

/*
    fun getAllByName(name: String): Observable<List<Subject>>
    fun getByGroup(group: String): Observable<List<Subject>>
    fun getByDay(day: String): Observable<List<Subject>>
    fun getByNameAndGroup(name: String): Observable<List<Subject>>
    fun getByNameAndGroupAndDay(name: String): Observable<List<Subject>>
    fun getByNameAndDay(name: String): Observable<List<Subject>>
    fun getByGroupAndDay(group: String): Observable<List<Subject>>
    fun insert(movie: Subject): Completable*/
}