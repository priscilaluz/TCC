/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcc.service.persistence;

/**
 * Interface do tratador de transações programáticas.
 * 
 * 
 */
public interface TransactionHandler {

    Object handle(TransactionCallback callback);
    
}
