package com.project.millie

import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File

@BindingAdapter("imgFile")
fun ImageView.setImage(file: File?) {
    Glide.with(this).load(file).error(R.mipmap.ic_launcher).skipMemoryCache(true)
        .into(this)
}
@BindingAdapter("imgUrlOrDownload","fileName")
fun ImageView.setDownloadImage(imgUrl: String?,fileName : String) {
    imgUrl?.let {
        val file = File(context.filesDir, "${fileName.replace("\\W".toRegex(),"")}.jpg")
        if(file.exists()){
            Log.i("hsik","exists")
            this.setImage(file)
        }else{
            Glide.with(this).download(imgUrl).addListener(object : RequestListener<File>{
                override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<File>, isFirstResource: Boolean): Boolean{
                    Log.i("hsik","onLoadFailed = $e")
                    return false
                }
                override fun onResourceReady(resource: File, model: Any, target: Target<File>?, dataSource: DataSource, isFirstResource: Boolean): Boolean {
                    resource.inputStream().copyTo(file.outputStream()){
                        Log.i("hsik","filename[$it] = $fileName" )
                        CoroutineScope(Dispatchers.Main).launch{
                            this@setDownloadImage.setImage(file)
                        }
                    }
                    return false
                }
            }).submit()
        }

    }?:return this@setDownloadImage.setImage(null)


}