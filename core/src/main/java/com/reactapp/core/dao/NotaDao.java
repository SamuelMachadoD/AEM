package com.reactapp.core.dao;

import com.reactapp.core.models.NotaFiscal;

import java.util.List;

public interface NotaDao {

    List<NotaFiscal> getNotaID(int id);
}
