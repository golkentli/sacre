/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.alari.sacre;

/**
 *
 * @author onur
 */

@FunctionalInterface
public interface ParameterPrecondition {
        
        public boolean test();
        
//        @Override
//        public String toString();
    }