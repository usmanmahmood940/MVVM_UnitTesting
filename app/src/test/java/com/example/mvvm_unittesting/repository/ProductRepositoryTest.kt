package com.example.mvvm_unittesting.repository

import com.example.mvvm_unittesting.api.ProductsAPI
import com.example.mvvm_unittesting.util.NetworkResult
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.Response

class ProductRepositoryTest {

    @Mock
    lateinit var productsApi: ProductsAPI

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun testGetPrdoucts_EmptyList() = runTest{
        Mockito.`when`(productsApi.getProducts()).thenReturn(Response.success(emptyList()))

        val sut = ProductRepository(productsApi)
        val result = sut.getProducts()

        assertEquals(0, result.data?.size)

    }

    @After
    fun tearDown() {
    }
}