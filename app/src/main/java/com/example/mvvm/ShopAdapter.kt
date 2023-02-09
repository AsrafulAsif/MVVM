package com.example.mvvm
//new
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import kotlin.math.ceil

class ShopAdapter (
    private val context: Context,
    private var list: List<Shop>,
        ) :RecyclerView.Adapter<ShopAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image: ImageView = view.findViewById(R.id.banner)
        val restartLogoImageView : ImageView = view.findViewById(R.id.restartLogoImageView)
        val name: TextView = view.findViewById(R.id.resName)
        val subTitle: TextView = view.findViewById(R.id.subtitle)
        val closeShop: TextView = view.findViewById(R.id.closeShop)
        val discount: TextView = view.findViewById(R.id.discount)
        val duration: TextView = view.findViewById(R.id.duration)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopAdapter.ViewHolder {
        val view =
            LayoutInflater.from(context)
                .inflate(R.layout.custom_shop_row, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = list[position]

        if (data != null) {

            if (data.status.equals("Open")) {
                if (data.timeSlots.isNullOrEmpty()) {
                    holder.closeShop.visibility = View.GONE
                    holder.duration.visibility = View.VISIBLE
                    Glide.with(context)
                        .load(data.banner)
                        .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                        .into(holder.image)
                } else {
                    var isClose = false
                    val currentTime = Utils.getCurrentTime("HH:mm:ss")
                    for (i in 0 until data.timeSlots.size) {
                        val startTime = data.timeSlots[i].from
                        val endTime = data.timeSlots[i].to
//                        val startTime = "10:00:00"
//                        val endTime = "20:00:00"
                        startTime.let {
                            endTime.let { it1->
                                currentTime?.let { it2 ->
                                    isClose = Utils.isTimeInside(it, it1, it2)

                                }

                            }
                        }
                        if (isClose) {
                            break
                        }
                    }

                    if (!isClose) {
                        holder.closeShop.visibility = View.VISIBLE
                        holder.duration.visibility = View.GONE
                        Glide.with(context)
                            .load(data.banner)
                            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                            .into(holder.image)
                    } else {
                        holder.closeShop.visibility = View.GONE
                        holder.duration.visibility = View.VISIBLE
                        Glide.with(context)
                            .load(data.banner)
                            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                            .into(holder.image)
                    }


                }
            }
            else
            { holder.closeShop.visibility = View.VISIBLE }


            Glide.with(context)
                .load(data.logo)
                .apply(RequestOptions.bitmapTransform(RoundedCorners(14)))
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .into(holder.restartLogoImageView)

            holder.name.text = data.name
            val charge = data.deliveryCharge?.toDouble()?.let { ceil(it) }
            if (charge == null) {
                holder.subTitle.text = "Max. Tk 50 "


            }else{
                holder.subTitle.text = "Tk $charge"
            }


//            holder.subTitle.text = "TK. ${data.deliveryCharge}"
            //  holder.shopRating.text = data.rating?.toDouble().toString()

            val deliveryTime = data.timeRemaining?.toInt()
            if (deliveryTime == null){
                holder.duration.text = "Min. 30 mins"
            }else{
                holder.duration.text = convertMinToHourMin(deliveryTime.toInt())
            }
        }
//        holder.itemView.setOnClickListener {
//            data?.let { it1 -> interaction?.onShopItemSelected(position, it1) }
//        }

    }

    private fun convertMinToHourMin(min: Int): String {
        val hr = min / 60
        val mins = min % 60

        return if (hr <= 0) {
            "${mins}mins"
        } else {
            "${hr}hr & ${mins}mins"
        }

    }

    override fun getItemCount(): Int {
        return list.count()
    }
    fun setList(campaigns: List<Shop>?) {
        if (campaigns != null) {
            this.list = campaigns
        }
    }
}