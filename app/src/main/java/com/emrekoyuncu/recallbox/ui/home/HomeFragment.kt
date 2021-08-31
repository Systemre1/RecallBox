package com.emrekoyuncu.recallbox.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.emrekoyuncu.recallbox.R
import com.emrekoyuncu.recallbox.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val myWebView: WebView = _binding!!.webview
        myWebView.getSettings().setDomStorageEnabled(true)
        myWebView.getSettings().setJavaScriptEnabled(true)
        myWebView.getSettings().setUseWideViewPort(true)
        myWebView.loadUrl("https://www.bbc.com/turkce/topics/cnq68n6wgzdt")
    }
}