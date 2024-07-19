package com.example.mvvm_unittesting

import com.example.mvvm_unittesting.api.ProductsAPI
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class ProductApiTest {

    lateinit var mockWebServer: MockWebServer
    lateinit var productsAPI: ProductsAPI

    @Before
    fun setup(){
        mockWebServer = MockWebServer()
        productsAPI = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(ProductsAPI::class.java)
    }

    @Test
    fun testGetProducts() = runTest{
        val mockResponse = MockResponse()
        mockResponse.setBody("[]")
        mockWebServer.enqueue(mockResponse)

        val response = productsAPI.getProducts()
        mockWebServer.takeRequest()

        assertEquals(true,response.body()!!.isEmpty())

    }

    @Test
    fun testGetProducts_returnProducts() = runTest{
        val mockResponse = MockResponse()
        val content = Helper.readFileResource("/response.json")
        mockResponse.setResponseCode(200)
        mockResponse.setBody(content)
        mockWebServer.enqueue(mockResponse)

        val response = productsAPI.getProducts()
        mockWebServer.takeRequest()

        assertEquals(false,response.body()!!.isEmpty())
        assertEquals(2,response.body()!!.size)

    }



    @After
    fun tearDown(){
        mockWebServer.shutdown()
    }
}