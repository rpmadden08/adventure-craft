package com.madbros.adventurecraft.LevelTypes.FractalTypes;

import java.awt.Dimension;

import javax.swing.JFrame;

import com.sudoplay.joise.module.Module;
import com.sudoplay.joise.module.ModuleAutoCorrect;
import com.sudoplay.joise.module.ModuleFractal;
import com.sudoplay.joise.module.ModuleScaleOffset;
import com.sudoplay.joise.module.ModuleSelect;
import com.sudoplay.joise.module.ModuleTranslateDomain;
import com.sudoplay.joise.module.ModuleBasisFunction.BasisType;
import com.sudoplay.joise.module.ModuleBasisFunction.InterpolationType;
import com.sudoplay.joise.module.ModuleFractal.FractalType;
//import com.sudoplay.main.Canvas;

public class CaveNoise extends BasicNoise{
	//public ModuleAutoCorrect noise;

	public CaveNoise(int seed) {
		super(seed);

		    // ========================================================================
		    // = Joise module chain
		    // ========================================================================

		    /*
		     * Start with a fractal generator...
		     */

		
		 // cave_shape
	    ModuleFractal caveShape = new ModuleFractal(FractalType.RIDGEMULTI, BasisType.GRADIENT, InterpolationType.QUINTIC);
	    caveShape.setNumOctaves(1);
	    caveShape.setFrequency(10);
	    caveShape.setSeed(seed);
	    
	    ModuleSelect caveSelect = new ModuleSelect();
	    caveSelect.setLowSource(1);
	    caveSelect.setHighSource(0);
	    caveSelect.setThreshold(0.8);
	    caveSelect.setFalloff(0);
	    caveSelect.setControlSource(caveShape);
	    
	    ModuleFractal cavePerturbFractal = new ModuleFractal(FractalType.FBM, BasisType.GRADIENT, InterpolationType.QUINTIC);
	    cavePerturbFractal.setNumOctaves(6);
	    cavePerturbFractal.setFrequency(3);
	    cavePerturbFractal.setSeed(seed);
	    
	    
	    // cave_perturb_scale
	    ModuleScaleOffset cavePerturbScale = new ModuleScaleOffset();
	    cavePerturbScale.setScale(0.25);
	    cavePerturbScale.setOffset(0);
	    cavePerturbScale.setSource(cavePerturbFractal);

	    // cave_perturb
	    ModuleTranslateDomain cavePerturb = new ModuleTranslateDomain();
	    cavePerturb.setAxisXSource(cavePerturbScale);
	    cavePerturb.setSource(caveSelect);

	    

		    /*
		     * ... route it through an autocorrection module...
		     * 
		     * This module will sample it's source multiple times and attempt to
		     * auto-correct the output to the range specified.
		     */
		    noise = new ModuleAutoCorrect();
		    noise.setSource(caveSelect); // set source (can usually be either another Module or a
		                       // double value; see specific module for details)
		    noise.setRange(0.0f, 1.0f); // set the range to auto-correct to
		    noise.setSamples(10000); // set how many samples to take
		    noise.calculate(); // perform the caclulations
		    System.out.println("CHECK");

	}
}
