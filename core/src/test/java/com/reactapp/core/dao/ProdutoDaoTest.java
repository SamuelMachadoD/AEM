package com.reactapp.core.dao;

import com.reactapp.core.models.Produto;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import junit.framework.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;

import java.util.List;

@ExtendWith(AemContextExtension.class)
public class ProdutoDaoTest {

    @Test
    void ListaProdutoID(){
        ProdutoDao mockito = Mockito.mock(ProdutoDao.class);
        Produto produto = mockito.listaProdutoID(1);
        Assert.assertNull(produto);
    }

    @Test
    void ListaProdutoLista(){
        ProdutoDao mockito = Mockito.mock(ProdutoDao.class);
        List<Produto> produtos = mockito.listaProduto();
        Assert.assertTrue(produtos.isEmpty());
    }
}
