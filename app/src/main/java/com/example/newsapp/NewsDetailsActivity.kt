package com.example.newsapp

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.net.Uri
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.newsapp.databinding.ActivityNewsDetailsBinding
import com.example.newsapp.recycler2.model.DataX
import kotlinx.android.synthetic.main.activity_news_details.*

class NewsDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNewsDetailsBinding
    var news: DataX? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityNewsDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupActionBar()

        var i=intent
        news=i.getParcelableExtra<DataX>("news")
        overridePendingTransition(R.anim.slide_up, R.anim.slide_down)

        binding.tvDate.text=news!!.date
        binding.authorName.text=news!!.author
        binding.bitcoinSol.text=news!!.content
        binding.theCrypto.text=news!!.title
        Glide.with(this).load(news!!.imageUrl).into(rectangle_3)

        binding.readFullS.append("Read Full Story")

        val text="Read Full Story"
        val ss=SpannableString(text)
        val clickableSpan: ClickableSpan=object : ClickableSpan() {
            override fun onClick(textView: View) {

                val browserIntent=Intent(Intent.ACTION_VIEW,Uri.parse(news!!.readMoreUrl))
                browserIntent.setPackage("com.android.chrome")
                startActivity(browserIntent)

                Toast.makeText(this@NewsDetailsActivity, "clicked", Toast.LENGTH_SHORT).show()
            }
        }

        ss.setSpan(clickableSpan, 0, 15, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        ss.setSpan(StyleSpan(Typeface.BOLD), 0, 15, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        ss.setSpan(ForegroundColorSpan(Color.BLACK), 0, 15, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)


        binding.readFullS.text=ss
        binding.readFullS.movementMethod=LinkMovementMethod.getInstance()

    }

    private fun setupActionBar() {

        setSupportActionBar(binding.toolbar)
        val actionBar=supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setDisplayShowTitleEnabled(false)
            actionBar.setHomeAsUpIndicator(R.drawable.arrow_back)
        }
        binding.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.rightMenu -> {
                onClick()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun onClick() {
        val sendIntent=Intent()
        sendIntent.action=Intent.ACTION_SEND
        sendIntent.putExtra(Intent.EXTRA_TEXT,news!!.readMoreUrl )
        sendIntent.type="text/plain"
        sendIntent.setPackage("com.whatsapp")
        startActivity(Intent.createChooser(sendIntent, ""))
        startActivity(sendIntent)

    }
}