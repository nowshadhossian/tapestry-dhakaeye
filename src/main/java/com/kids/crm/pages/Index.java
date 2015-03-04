package com.kids.crm.pages;

import java.util.Date;
import java.util.Random;

import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Property;



/**
 * Start page of application tapestryHiber.
 */
public class Index
{
	 private final Random random = new Random(System.nanoTime());

	  @InjectPage
	  private Guess guess;


	public Date getCurrentTime() 
	{ 
		return new Date(); 
	}
	Object onActionFromStart(){
		int target = random.nextInt(10) + 1;

	    guess.setup(target);

	    return guess;
	}
}
