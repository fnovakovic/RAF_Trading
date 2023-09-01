package rs.raf.jun_Filip_Novakovic_882020RN.presentation.view.recycler.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import rs.raf.jun_Filip_Novakovic_882020RN.data.model_stocks.Stocks
import rs.raf.jun_Filip_Novakovic_882020RN.databinding.StockItemBinding


class StockHolder(private val itemBinding: StockItemBinding) : RecyclerView.ViewHolder(itemBinding.root) {

    fun bind(stock: Stocks) {
        itemBinding.id.text = stock.symbol
        itemBinding.name.text = stock.name
        itemBinding.userIdd.text = stock.last

     /*   var data: Charts[]
        var entriess: List<Entry> = ArrayList<Entry>();
        for (Charts data : dataObjects) {
            // turn your data into Entry objects
            entries.add(new Entry(data.getValueX(), data.getValueY()));
        }

        var entries: ArrayList<Entry> = ArrayList()
        entries.add(Entry(stock.chart.bars)
        val chart = findViewById(R.id.chart) as LineChart
        val dataSet = LineDataSet(stock.chart.bars, "Label") // add entries to dataset*/

      /*  var xvalue = ArrayList<Charts>()
        for(i in stock.chart.bars){
            xvalue.add(Charts(i.price,i.time))
        }


        var lineEntries = ArrayList<Entry>()
        lineEntries.add(Entry(2.5F, 0F))
        lineEntries.add(Entry(3.5F, 1F))
        lineEntries.add(Entry(4.5F, 2F))
        lineEntries.add(Entry(5.5F, 3F))



        var linedataSet =  LineDataSet(lineEntries,"First")



        val data = LineData(xvalue,linedataSet)
        */

        //Part1
 /*       val entries = ArrayList<Entry>()

//Part2
        entries.add(Entry(1f, 10f))
        entries.add(Entry(2f, 2f))
        entries.add(Entry(3f, 7f))
        entries.add(Entry(4f, 20f))
        entries.add(Entry(5f, 16f))

//Part3
        val vl = LineDataSet(entries, "My Type")

//Part4
        vl.setDrawValues(false)
        vl.setDrawFilled(true)
        vl.lineWidth = 3f
        vl.fillColor = R.color.darker_gray
        vl.fillAlpha = R.color.holo_red_light

//Part5
        itemBinding.chart.xAxis.labelRotationAngle = 0f

//Part6
        itemBinding.chart.data = LineData(vl)

//Part7
        itemBinding.chart.axisRight.isEnabled = false
        itemBinding.chart.xAxis.axisMaximum = j+0.1f

//Part8
        itemBinding.chart.setTouchEnabled(true)
        itemBinding.chart.setPinchZoom(true)

//Part9
        itemBinding.chart.description.text = "Days"
        itemBinding.chart.setNoDataText("No forex yet!")

//Part10
        itemBinding.chart.animateX(1800, Easing.EaseInExpo)

//Part11
        val markerView = CustomMarker(this@ShowForexActivity, R.layout.marker_view)
        itemBinding.chart.marker = markerView


*/


        var entries : ArrayList<Entry>  =  ArrayList<Entry>();
        for (d in stock.chart.bars  ) {
            // turn your data into Entry objects
            entries.add( Entry(0F, 0F));
            entries.add( Entry(0F, 1F));
            entries.add( Entry(0F, 2F));
            entries.add( Entry(0F, 3F));
        }

        var dataset = LineDataSet(entries, "Label"); // add entries to dataset

        val lineData = LineData(dataset)
        itemBinding.chart.setData(lineData)
        itemBinding.chart.invalidate() // refresh


    }

}