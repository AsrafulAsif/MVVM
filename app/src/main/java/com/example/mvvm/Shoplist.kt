package com.example.mvvm
//new
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvm.databinding.FragmentShoplistBinding
import kotlinx.coroutines.launch

class Shoplist : Fragment(R.layout.fragment_shoplist) {
    private lateinit var binding: FragmentShoplistBinding

   private  val viewModel: ShoplistViewModel by viewModels()
    private lateinit var shopAdapter: ShopAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentShoplistBinding.bind(view)
        binding.rs.apply {
                    layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            shopAdapter = ShopAdapter(requireContext(), emptyList())
            adapter = shopAdapter
        }

        viewModel.requestForRestaurantAroundYou("Q_FOOD",23.778647531640836,90.4259375091631,3)
        lifecycleScope.launch {
            viewModel.observeMovieLiveData().observe(viewLifecycleOwner) {
                shopAdapter.setList(it?.shops)
                shopAdapter.notifyDataSetChanged()
            }
        }
    }


}