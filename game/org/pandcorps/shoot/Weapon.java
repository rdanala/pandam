package org.pandcorps.shoot;

import java.util.HashSet;
import java.util.List;

import org.pandcorps.core.Coltil;
import org.pandcorps.core.Mathtil;
import org.pandcorps.core.Pantil;
import org.pandcorps.game.actor.Burst;
import org.pandcorps.pandam.*;
import org.pandcorps.pandam.event.*;
import org.pandcorps.pandax.Pandy;

public class Weapon extends Panctor {
    /*package*/ final static int INF = Integer.MAX_VALUE;
    
    public final static class WeaponParameter {
        public final String name;
        public final int min;
        public final int max;
        
        public WeaponParameter(final String name, final int min, final int max) {
            this.name = name;
            this.min = min;
            this.max = max;
        }
        
        public final boolean isUpgradeApplicable() {
            return min < max;
        }
    }
    
	public final static class WeaponDefinition {
		/*package*/ final String name;
		private final Panmage image;
		private final Panimation flashAnm;
		private final Panimation casingAnm;
		private final Panimation smokeAnm;
		private final Panimation attackAnm;
		protected final Emitter[] attackEmitters;
		protected final Emitter[] attackingEmitters;
		private final int delay; // move into WeaponParameter
		/*package*/ final WeaponParameter power;
		/*package*/ final WeaponParameter capacity;
		//WeaponParameter rate
		/*package*/ final WeaponParameter pierce;
		/*package*/ final WeaponParameter spray;
		
		public WeaponDefinition(final String name, final Panmage image, final Panimation flashAnm,
				final Panimation casingAnm, final Panimation smokeAnm, final Panimation attackAnm,
				final Emitter[] attackEmitters, final Emitter[] attackingEmitters,
				final int delay, final int minPower, final int maxPower,
				final int minCapacity, final int maxCapacity,
				final int minPierce, final int maxPierce,
				final int minSpray, final int maxSpray) {
			this.name = name;
			this.image = image;
			this.flashAnm = flashAnm;
			this.casingAnm = casingAnm;
			this.smokeAnm = smokeAnm;
			this.attackAnm = attackAnm;
			this.attackEmitters = attackEmitters;
			this.attackingEmitters = attackingEmitters;
			// step occurs in same cycle after setting delay, 1 acts like 0 if we don't add 1
			this.delay = delay <= 0 ? 0 : delay + 1;
			this.power = new WeaponParameter("Power", minPower, maxPower);
			this.capacity = new WeaponParameter("Capacity", minCapacity, maxCapacity);
			this.pierce = new WeaponParameter("Pierce", minPierce, maxPierce);
			this.spray = new WeaponParameter("Spray", minSpray, maxSpray);
		}
	}
	
	public final static class WeaponArgument {
	    public final WeaponParameter parm;
	    
	    private int val;
	    
	    public WeaponArgument(final WeaponParameter parm) {
	        this.parm = parm;
	        val = parm.min;
	    }
	    
	    public boolean isUpgradePossible() {
	        return val < parm.max;
	    }
	    
	    public boolean upgrade() {
	        if (!isUpgradePossible()) {
	            return false;
	        }
	        this.val++;
	        return true;
	    }
	    
	    public final int getValue() {
	        return val;
	    }
	}
	
	protected final WeaponDefinition def;
	private final WeaponArgument power;
	private final WeaponArgument capacity;
	private final WeaponArgument pierce;
	private final WeaponArgument spray;
	private final List<WeaponArgument> args;
	private boolean attacking = false;
	private int timer = 0;
	private int smoke = 0;
	
	protected Weapon(final WeaponDefinition def) {
		super(Pantil.vmid());
		this.def = def;
		power = new WeaponArgument(def.power);
		capacity = new WeaponArgument(def.capacity);
		pierce = new WeaponArgument(def.pierce);
		spray = new WeaponArgument(def.spray);
		args = Coltil.unmodifiableList(Coltil.asList(power, capacity, pierce, spray));
		setView(def.image);
	}
	
	protected final void step() {
		if (attacking) {
			attacking = false;
		} else {
			changeView(def.image);
		}
		if (timer > 0) {
			timer--;
		}
		if (smoke > 0) {
		    smoke--;
		}
	}
	
	protected final void attack(final Shooter shooter, final Emitter[] emitters) {
		if (emitters == null) {
			return;
		}
		attacking = true;
		if (def.attackAnm != null) {
			changeView(def.attackAnm);
		}
		if (timer > 0) {
			return;
		}
		timer = def.delay;
		final boolean mirror = isMirror();
		final HashSet<Panple> projPositions = new HashSet<Panple>();
		final int spray = this.spray.val;
		for (int i = 0; i < spray; i++) {
    		for (final Emitter em : emitters) {
    			final Projectile p = (Projectile) em.emit(shooter, mirror);
    			projPositions.add(p.getPosition());
    			p.weapon = this;
    		}
		}
		if (def.flashAnm != null) {
			for (final Panple projPos : projPositions) {
				final Burst flash = new Burst(def.flashAnm);
				flash.setMirror(mirror);
				flash.getPosition().set(projPos);
				getLayer().addActor(flash);
			}
		}
		final int mult = mirror ? -1 : 1;
		if (def.casingAnm != null) {
		    for (final Panple projPos : projPositions) {
		        final Casing casing = new Casing(def.casingAnm);
		        casing.setMirror(mirror);
		        casing.getPosition().set(projPos.getX() - (7 * mult), projPos.getY(), projPos.getZ());
		        final float rx = Mathtil.randf(1, 2.5f), ry = Mathtil.randf(2.5f, 4.5f);
		        casing.getVelocity().set(-mult * rx, ry);
		        casing.getAcceleration().setY(-.75f);
                getLayer().addActor(casing);
		    }
		}
		if (def.smokeAnm != null && smoke <= 0) {
		    for (final Panple projPos : projPositions) {
		        final Casing casing = new Casing(def.smokeAnm);
		        casing.setMirror(mirror);
		        casing.getPosition().set(projPos.getX() - (7 * mult), projPos.getY(), projPos.getZ());
		        final float rx = Mathtil.randf(-1.25f, 1.25f), ry = Mathtil.randf(0.1f, 0.5f);
		        casing.getVelocity().set(rx, ry);
		        casing.getAcceleration().setY(.25f);
                getLayer().addActor(casing);
                smoke = 5;
		    }
		}
	}
	
	public WeaponArgument getPower() {
        return power;
    }
	
	public WeaponArgument getCapacity() {
        return capacity;
    }
	
	public WeaponArgument getPierce() {
		return pierce;
	}
	
	public WeaponArgument getSpray() {
	    return spray;
	}
	
	public List<WeaponArgument> getArguments() {
	    return args;
	}
	
	private final static class Casing extends Pandy implements AnimationEndListener {
        public Casing(final Panimation anm) {
            super(Pantil.vmid());
            setView(anm);
        }

        @Override
        public final void onAnimationEnd(final AnimationEndEvent event) {
            destroy();
        }
	}
}
