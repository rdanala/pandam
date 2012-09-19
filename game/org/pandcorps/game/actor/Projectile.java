/*
Copyright (c) 2009-2011, Andrew M. Martin
All rights reserved.

Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following
conditions are met:

 * Redistributions of source code must retain the above copyright notice, this list of conditions and the following
   disclaimer.
 * Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following
   disclaimer in the documentation and/or other materials provided with the distribution.
 * Neither the name of Pandam nor the names of its contributors may be used to endorse or promote products derived from this
   software without specific prior written permission.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING,
BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO
EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR
TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
POSSIBILITY OF SUCH DAMAGE.
*/
package org.pandcorps.game.actor;

import org.pandcorps.core.Pantil;
import org.pandcorps.pandam.*;
import org.pandcorps.pandam.event.*;
import org.pandcorps.pandam.event.boundary.*;

public abstract class Projectile extends Panctor implements StepListener, AllOobListener, Collidable /*Or CollisionListener if we want two Projectiles to collide with each other*/ {
    
    /*package*/ Emitter emitter = null;
    protected Panple vel = null;
    protected byte time;
    protected byte age = 0;
    
    public Projectile() {
        super(Pantil.vmid());
    }
    
    protected void init(final Guy2 guy, final Emitter emitter, final boolean mirror) {
        this.emitter = emitter;
        this.vel = mirror ? emitter.mirVel : emitter.vel;
        this.time = emitter.time;
        setView(emitter.projView);
        final Panple pos = guy.getPosition();
        final int mult = mirror ? -1 : 1; // Maybe add Panctor.getMirrorMultiplier()
        getPosition().set(pos.getX() + (emitter.xoff * mult), pos.getY() + emitter.yoff, pos.getZ() + 1);
        setMirror(mirror);
        guy.getLayer().addActor(this);
    }
    
    private final void setView(final Panview view) {
    	if (view instanceof Panmage) {
            setView((Panmage) view);
        } else {
            setView((Panimation) view);
        }
    }
    
    @Override
    public final void onStep(final StepEvent event) {
        age++;
        if (time > 0) {
            time--;
        }
        if (time == 0) {
            die();
        }
        if (vel != null) {
            getPosition().add(vel);
        }
    }
    
    @Override
    public final void onAllOob(final AllOobEvent event) {
        die();
    }
    
    public abstract void die();
}
