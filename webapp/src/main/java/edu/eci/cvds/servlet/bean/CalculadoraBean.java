package edu.eci.cvds.servlet.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ApplicationScoped;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.Arrays;

@ManagedBean(name= "CalculadoraBean")
@ApplicationScoped

public class CalculadoraBean {
    public ArrayList<Integer> nums;
    public String type;
    public double result;

    public CalculadoraBean(){
        nums = new ArrayList<Integer>();
    }
    public void calculate(){
        switch (type){
            case "Mean":
                calculateMean();
                break;
            case "StandartDeviation":
                calculateStandartDeviation();
                break;
            case "Mode":
                calculateMode();
                break;
            case "Variance":
                calculateVariance();
                break;
        }
    }

    public void calculateMean(){

    }

    public void calculateStandartDeviation(){

    }

    public void calculateVariance(){

    }

    public void calculateMode(){
        int valueAppear1;
        int valueAppear2=0;
        int mode=0;
        boolean manyModes=false;
        for (int num:this.nums) {
            valueAppear1=0;
            for (int i = 0; i < nums.size(); i++) {
                if (num==nums.get(i)){
                    valueAppear1++;
                }
            }
            if (valueAppear1>valueAppear2){
                mode=num;
                valueAppear2=valueAppear1;
            }else if (valueAppear1==valueAppear2){
                manyModes=true;
            }
        }
        if (!manyModes){
            mode=0;
        }
        setResult(mode);
        System.out.println(this.result);
    }

    public void restart(){
        nums.clear();
        System.out.println(this.nums);
    }

    public ArrayList<Integer> getNums() {
        return nums;
    }

    public void setNums(String nums) {
        ArrayList<String> lista = new ArrayList<>(Arrays.asList(nums.split(";")));
        for (String num: lista) {
            this.nums.add(Integer.parseInt(num));
        }
        System.out.println(this.nums);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }
}

