package br.com.vitorsalgado.example.features.splash

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import br.com.vitorsalgado.example.R

class SplashFragment : Fragment() {
  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? = inflater.inflate(R.layout.splash_fragment, container, false)

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    findNavController().navigate(R.id.action_splash_to_home)
//    Handler().postDelayed({
//      context?.let {
//        findNavController().navigate(R.id.action_splash_to_home)
//      }
//    }, 2500)
  }
}
