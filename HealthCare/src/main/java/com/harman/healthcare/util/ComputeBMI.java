package com.harman.healthcare.util;


import com.harman.healthcare.model.Examination;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Created by inmkumar022 on 6/1/2017.
 */

public class ComputeBMI {

    public static final Logger LOG = LoggerFactory.getLogger(ComputeBMI.class);
    double bodyMassIndex;

    public void calBMI (Examination examination) {
        String name= examination.getName();
        double height= examination.getHeight();
        double weight= examination.getWeight();
        calculateBMI(name,height,weight);
    }

    public double calculateBMI(String name,double height,double weight){

        LOG .info("Underweight: less than 18.5");
        LOG .info("Normal:      between 18.5 and 24.9");
        LOG .info("Overweight:  between 25 and 29.9");
        LOG .info("Obese:       30 or greater");

        LOG .info(".........C A L C U L A T I N G         B O D Y     M A S S    I N D E X..................");
        LOG .info("Body Mass Index of");
        LOG .info("Name:"+name+","+"Heigth:"+height+","+"Weight:"+weight);

        bodyMassIndex = (weight / (height * height));
        if(bodyMassIndex<18.5){
            LOG .info("Underweight BMI");
        }else if(bodyMassIndex>18.5 && bodyMassIndex<24.9){
            LOG .info("Normal BMI");
        }else if(bodyMassIndex>25 && bodyMassIndex<29.9){
            LOG .info("Overweight BMI");
        }
        else if(bodyMassIndex==30 || bodyMassIndex>30){
            LOG .info("Obese BMI");
        }
        else{
            LOG .info("Invalid input");
        }
        return bodyMassIndex;
    }


}
