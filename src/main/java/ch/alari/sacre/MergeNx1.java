/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 2009 Onur Derin  All rights reserved.
 *
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 *  are met:
 *
 *     * Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above
 *       copyright notice, this list of conditions and the following
 *       disclaimer in the documentation and/or other materials provided
 *       with the distribution.
 *     * Neither the name of the <ORGANIZATION> nor the names of its
 *       contributors may be used to endorse or promote products derived
 *       from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS
 * FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE
 * COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT,
 * INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING,
 * BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT
 * LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN
 * ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */

package ch.alari.sacre;

import java.util.Map;


/**
 *
 * @author Onur Derin <oderin at users.sourceforge.net>
 */
public class MergeNx1 extends Component
{
    protected OutPort<Token> out;
    private ParameterDescriptor<Integer> n;
    
    public MergeNx1(String name, Map<String, String> params)
    {
        super(name, params);
        setType("mergenx1");
        setDescription("Giriş kapılarındaki tokenleri tek bir çıkış kapısına gönderir.");
        
        out = new OutPort<>(this);
        
        // define n
        n = new ParameterDescriptor<>(this, "n", false, 2, ParameterDescriptor.integerConverter);
        n.setDescription("Giriş kapısı adedi.");
        
        // set parameters
        initParameters();
        
//        int n = 2; // default value
//        if(params != null)
//        {
//            if( params.get("n") != null )
//            {
//                n = new Integer(params.get("n"));
//            }
//        }
        
        for(int i=0; i<n.getValue(); i++)
        {
            new InPort<Token>(this); // gets added to output ports list
        }
    }
    
    public void task() throws InterruptedException, Exception
    {
        for(InPort p: getInPorts())
        {
            Token t = p.take(); // if this take() receives a STOP_TOKEN, then null is returned by take() (also for consequent calls after the STOP_TOKEN).
            if(!p.isStopped())
            {
                out.put(t);
            }
        }    
    }

    public InPort getIn(int i)
    {
        return getInPorts().get(i);
    }
    
    public OutPort<Token> getOut()
    {
        return out;
    }
}
