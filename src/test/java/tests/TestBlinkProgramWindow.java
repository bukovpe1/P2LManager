/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import com.buky.p2lmanager.model.blinking.BlinkGraphValues;
import java.util.ArrayList;
import junit.framework.Assert;
import org.junit.Test;


/**
 *
 * @author bukov
 */
public class TestBlinkProgramWindow {
    
    @Test
    public void testTimeAxis1(){
        
        BlinkGraphValues bgv = new BlinkGraphValues(1, 0.1, 0.2, 4, true, false, 0, 0, 0, 0, false, false);
        bgv.compute();
        Assert.assertEquals(6, bgv.getTimeAxis().size());
    }
    
    @Test
    public void testTimeAxis2(){
        
        BlinkGraphValues bgv = new BlinkGraphValues(0, 0.1, 0.2, 4, true, false, 0, 0, 0, 0, false, false);
        bgv.compute();
        Assert.assertEquals(5, bgv.getTimeAxis().size());
    }
    
    @Test
    public void testTimeAxis3(){
        BlinkGraphValues bgv = new BlinkGraphValues(1, 0.1, 0.2, 4, true, false, 1, 0.2, 0.1, 4, false, false);
        bgv.compute();
        Assert.assertEquals(5, bgv.getTimeAxis().size());
    }
    
    @Test
    public void testTimeAxis4(){
        
        BlinkGraphValues bgv = new BlinkGraphValues(1, 0.1, 0.2, 3, true, false, 3, 0.1, 0.2, 4, false, false);
        bgv.compute();
        Assert.assertEquals(9, bgv.getTimeAxis().size());
    }
    
    @Test
    public void testTimeAxis5(){
        
        BlinkGraphValues bgv = new BlinkGraphValues(1, 1, 2, 4, false, false, 2.5, 0.3, 0.7, 5, true, false);
        bgv.compute();
        Assert.assertEquals(11, bgv.getTimeAxis().size());
    }
    
    private void printTimeAxis(ArrayList<Double> ta){
        for (int i = 0; i < ta.size(); i++) {
            System.out.println(ta.get(i));
        }
    }
    
}
