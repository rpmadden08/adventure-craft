package com.madbros.tileminer.LevelTypes.FractalTypes;


import com.sudoplay.joise.module.ModuleAutoCorrect;
import com.sudoplay.joise.module.ModuleFractal;
import com.sudoplay.joise.module.ModuleBasisFunction.BasisType;
import com.sudoplay.joise.module.ModuleBasisFunction.InterpolationType;
import com.sudoplay.joise.module.ModuleFractal.FractalType;
//import com.sudoplay.main.Canvas;

public class BasicNoise {
	public ModuleAutoCorrect noise;

	public BasicNoise(int seed) {

		    // ========================================================================
		    // = Joise module chain
		    // ========================================================================

		    /*
		     * Start with a fractal generator...
		     */
		    ModuleFractal gen = new ModuleFractal();
		    gen.setAllSourceBasisTypes(BasisType.SIMPLEX);
		    gen.setAllSourceInterpolationTypes(InterpolationType.CUBIC);
		    gen.setNumOctaves(10);//10
		    gen.setFrequency(1.2); //0.7 (adjust how large biomes are
		    gen.setType(FractalType.RIDGEMULTI);
		    gen.setSeed(seed);

		    /*
		     * ... route it through an autocorrection module...
		     * 
		     * This module will sample it's source multiple times and attempt to
		     * auto-correct the output to the range specified.
		     */
		    noise = new ModuleAutoCorrect();
		    noise.setSource(gen); // set source (can usually be either another Module or a
		                       // double value; see specific module for details)
		    noise.setRange(0.0f, 1.0f); // set the range to auto-correct to
		    noise.setSamples(10000); // set how many samples to take
		    noise.calculate(); // perform the caclulations

	}
}
