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
package org.pandcorps.core;

import javax.sound.midi.*;

// Music Utility
public final class Mustil {
    public final static int PRG_ACOUSTIC_GRAND_PIANO = 0;
    public final static int PRG_BRIGHT_ACOUSTIC_PIANO = 1;
    public final static int PRG_ELECTRIC_GRAND_PIANO = 2;
    public final static int PRG_HONKY_TONK_PIANO = 3;
    public final static int PRG_ELECTRIC_PIANO_1 = 4;
    public final static int PRG_ELECTRIC_PIANO_2 = 5;
    public final static int PRG_HARPSICHORD = 6;
    public final static int PRG_CLAVINET = 7;
    public final static int PRG_CELESTA = 8;
    public final static int PRG_GLOCKENSPIEL = 9;
    public final static int PRG_MUSIC_BOX = 10;
    public final static int PRG_VIBRAPHONE = 11;
    public final static int PRG_MARIMBA = 12;
    public final static int PRG_XYLOPHONE = 13;
    public final static int PRG_TUBULAR_BELLS = 14;
    public final static int PRG_DULCIMER = 15;
    public final static int PRG_DRAWBAR_ORGAN = 16;
    public final static int PRG_PERCUSSIVE_ORGAN = 17;
    public final static int PRG_ROCK_ORGAN = 18;
    public final static int PRG_CHURCH_ORGAN = 19;
    public final static int PRG_REED_ORGAN = 20;
    public final static int PRG_ACCORDION = 21;
    public final static int PRG_HARMONICA = 22;
    public final static int PRG_TANGO_ACCORDION = 23;
    public final static int PRG_ACOUSTIC_GUITAR_NYLON = 24;
    public final static int PRG_ACOUSTIC_GUITAR_STEEL = 25;
    public final static int PRG_ELECTRIC_GUITAR_JAZZ = 26;
    public final static int PRG_ELECTRIC_GUITAR_CLEAN = 27;
    public final static int PRG_ELECTRIC_GUITAR_MUTED = 28;
    public final static int PRG_OVERDRIVEN_GUITAR = 29;
    public final static int PRG_DISTORTION_GUITAR = 30;
    public final static int PRG_GUITAR_HARMONICS = 31;
    public final static int PRG_ACOUSTIC_BASS = 32;
    public final static int PRG_ELECTRIC_BASS_FINGER = 33;
    public final static int PRG_ELECTRIC_BASS_PICK = 34;
    public final static int PRG_FRETLESS_BASS = 35;
    public final static int PRG_SLAP_BASS_1 = 36;
    public final static int PRG_SLAP_BASS_2 = 37;
    public final static int PRG_SYNTH_BASS_1 = 38;
    public final static int PRG_SYNTH_BASS_2 = 39;
    public final static int PRG_VIOLIN = 40;
    public final static int PRG_VIOLA = 41;
    public final static int PRG_CELLO = 42;
    public final static int PRG_CONTRABASS = 43;
    public final static int PRG_TREMOLO_STRINGS = 44;
    public final static int PRG_PIZZICATO_STRINGS = 45;
    public final static int PRG_ORCHESTRAL_HARP = 46;
    public final static int PRG_TIMPANI = 47;
    public final static int PRG_STRING_ENSEMBLE_1 = 48;
    public final static int PRG_STRING_ENSEMBLE_2 = 49;
    public final static int PRG_SYNTH_STRINGS_1 = 50;
    public final static int PRG_SYNTH_STRINGS_2 = 51;
    public final static int PRG_CHOIR_AAHS = 52;
    public final static int PRG_VOICE_OOHS = 53;
    public final static int PRG_SYNTH_CHOIR = 54;
    public final static int PRG_ORCHESTRA_HIT = 55;
    public final static int PRG_TRUMPET = 56;
    public final static int PRG_TROMBONE = 57;
    public final static int PRG_TUBA = 58;
    public final static int PRG_MUTED_TRUMPET = 59;
    public final static int PRG_FRENCH_HORN = 60;
    public final static int PRG_BRASS_SECTION = 61;
    public final static int PRG_SYNTH_BRASS_1 = 62;
    public final static int PRG_SYNTH_BRASS_2 = 63;
    public final static int PRG_SOPRANO_SAX = 64;
    public final static int PRG_ALTO_SAX = 65;
    public final static int PRG_TENOR_SAX = 66;
    public final static int PRG_BARITONE_SAX = 67;
    public final static int PRG_OBOE = 68;
    public final static int PRG_ENGLISH_HORN = 69;
    public final static int PRG_BASSOON = 70;
    public final static int PRG_CLARINET = 71;
    public final static int PRG_PICCOLO = 72;
    public final static int PRG_FLUTE = 73;
    public final static int PRG_RECORDER = 74;
    public final static int PRG_PAN_FLUTE = 75;
    public final static int PRG_BLOWN_BOTTLE = 76;
    public final static int PRG_SHAKUHACHI = 77;
    public final static int PRG_WHISTLE = 78;
    public final static int PRG_OCARINA = 79;
    public final static int PRG_SQUARE = 80; // LEAD 1
    public final static int PRG_SAWTOOTH = 81; // LEAD 2
    public final static int PRG_CALLIOPE = 82; // LEAD 3
    public final static int PRG_CHIFF = 83; // LEAD 4
    public final static int PRG_CHARANG = 84; // LEAD 5
    public final static int PRG_VOICE = 85; // LEAD 6
    public final static int PRG_FIFTHS = 86; // LEAD 7
    public final static int PRG_BASS_LEAD = 87; // LEAD 8
    public final static int PRG_NEW_AGE = 88; // PAD 1
    public final static int PRG_WARM = 89; // PAD 2
    public final static int PRG_POLYSYNTH = 90; // PAD 3
    public final static int PRG_CHOIR = 91; // PAD 4
    public final static int PRG_BOWED = 92; // PAD 5
    public final static int PRG_METALLIC = 93; // PAD 6
    public final static int PRG_HALO = 94; // PAD 7
    public final static int PRG_SWEEP = 95; // PAD 8
    public final static int PRG_RAIN = 96; // FX 1
    public final static int PRG_SOUNDTRACK = 97; // FX 2
    public final static int PRG_CRYSTAL = 98; // FX 3
    public final static int PRG_ATMOSPHERE = 99; // FX 4
    public final static int PRG_BRIGHTNESS = 100; // FX 5
    public final static int PRG_GOBLINS = 101; // FX 6
    public final static int PRG_ECHOES = 102; // FX 7
    public final static int PRG_SCI_FI = 103; // FX 8
    public final static int PRG_SITAR = 104;
    public final static int PRG_BANJO = 105;
    public final static int PRG_SHAMISEN = 106;
    public final static int PRG_KOTO = 107;
    public final static int PRG_KALIMBA = 108;
    public final static int PRG_BAGPIPE = 109;
    public final static int PRG_FIDDLE = 110;
    public final static int PRG_SHANAI = 111;
    public final static int PRG_TINKLE_BELL = 112;
    public final static int PRG_AGOGO = 113;
    public final static int PRG_STEEL_DRUMS = 114;
    public final static int PRG_WOODBLOCK = 115;
    public final static int PRG_TAIKO_DRUM = 116;
    public final static int PRG_MELODIC_TOM = 117;
    public final static int PRG_SYNTH_DRUM = 118;
    public final static int PRG_REVERSE_CYMBAL = 119;
    public final static int PRG_GUITAR_FRET_NOISE = 120;
    public final static int PRG_BREATH_NOISE = 121;
    public final static int PRG_SEASHORE = 122;
    public final static int PRG_BIRD_TWEET = 123;
    public final static int PRG_TELEPHONE_RING = 124;
    public final static int PRG_HELICOPTER = 125;
    public final static int PRG_APPLAUSE = 126;
    public final static int PRG_GUNSHOT = 127;
	
	private Mustil() {
        throw new Error();
    }
	
	public final static void addNote(final Track track, final long tick, final int channel, final int key, final int vel) throws Exception {
		addNote(track, tick, 30, channel, key, vel);
	}
	
	public final static void addNote(final Track track, final long tick, final int dur, final int channel, final int key, final int vel) throws Exception {
		final ShortMessage onMessage = new ShortMessage();
		final ShortMessage offMessage = new ShortMessage();
		onMessage.setMessage(ShortMessage.NOTE_ON, channel, key, vel);
		offMessage.setMessage(ShortMessage.NOTE_OFF, channel, key, 0);
		track.add(new MidiEvent(onMessage, tick));
		track.add(new MidiEvent(offMessage, tick + dur));
	}
	
	public final static void setInstrument(final Track track, final int channel, final int program) throws Exception {
		final ShortMessage message = new ShortMessage();
		message.setMessage(ShortMessage.PROGRAM_CHANGE, channel, program, 0);
		track.add(new MidiEvent(message, 0));
	}
	
	/*
	Don't know how CONTROL_CHANGE 7 is supposed to work.
	This method doesn't seem to work as intended.
	Lowering the volume of a channel seems to permanently mute the Sequence after it has been played once.
	*/
	/*public final static void setVolume(final Track track, final long tick, final int channel, final int vol) throws Exception {
		final ShortMessage message = new ShortMessage();
		message.setMessage(ShortMessage.CONTROL_CHANGE, channel, 7, vol);
		track.add(new MidiEvent(message, tick));
	}*/
}
