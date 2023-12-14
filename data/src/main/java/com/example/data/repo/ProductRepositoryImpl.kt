package com.example.data.repo

import android.content.Context
import android.net.Uri
import android.util.Log
import com.example.data.mapper.Mapper
import com.example.data.mapper.ProductMapper
import com.example.myapplication.data.response.get_product_with_category.service.AuthenticationService
import com.example.myapplication.data.response.get_product_with_category.service.ProductService
import com.example.domain.model.Product
import com.example.domain.repo.AuthenticationRepository
import com.example.domain.repo.ProductRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.BufferedInputStream
import java.io.BufferedOutputStream
import java.io.File
import java.io.FileOutputStream
import java.nio.file.Files
import java.nio.file.StandardCopyOption
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    @ApplicationContext val context: Context ,
    private val productService: com.example.myapplication.data.response.get_product_with_category.service.ProductService
) : ProductRepository {
    override suspend fun getAllProducts(apiToken: String):    List<Product>  {
       return  try {
        val result = productService.getAllProducts(apiToken)
            Log.d("product--get",result.body().toString())
        ProductMapper.mapToProductList(result.body()!!.data)
        }catch (e:Exception) {
            throw Exception(e.message)
        }
    }

    override suspend fun deleteProduct(apiToken: String, productId: Int): Boolean {
       return try {
           val result = productService.deleteProducts(apiToken, productId)
          Log.d("product--delete",result.code().toString())
         true
       }catch (e:Exception) {
           throw Exception(e.message)
       }
    }

    override suspend fun upDateProduct(
        apiToken: String,
        productId: Int,
        name: String,
        description: String,
        price: Int
    ): Boolean {
       return try {
       val result = productService.updateProducts(apiToken , productId, name, description, price)
        Log.d("product--upDate",result.body().toString())
       true
       }catch (e:Exception) {
           throw Exception(e.message)
       }
    }

    override suspend fun addProduct(
        apiToken: String,
        name: String,
        description: String,
        price: Int ,
        uri: Uri
    ): Boolean {
        return try {
            val image = createPartFromFile(createFileFromUri(uri)!!)
            val result = productService.addProducts(apiToken , image,name, description, price)
            Log.d("product--add",result.body().toString())
            true
        }catch (e:Exception) {
            throw Exception(e.message)
        }
    }



    private fun createFileFromUri(uri: Uri): File? {
        return try {
            val stream = context.contentResolver.openInputStream(uri)
            val file = File.createTempFile("${System.currentTimeMillis()}", ".png", context.cacheDir)
            val input = BufferedInputStream(stream)
            val output = BufferedOutputStream(FileOutputStream(file))
            val buffer = ByteArray(1024)
            var lengthRead: Int
            while (input.read(buffer).also { lengthRead = it } != -1) {
                output.write(buffer, 0, lengthRead)
            }
            output.flush()
            output.close()
            input.close()
            file
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    private fun createPartFromFile(file: File): MultipartBody.Part {
        val requestBody = RequestBody.create("image/*".toMediaTypeOrNull(), file)
        return MultipartBody.Part.createFormData("images[]" , file.name, requestBody)
    }

}