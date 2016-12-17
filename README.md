# BlockParticles

This is a relatively simple Bukkit plugin that allows server operators to place
decorative "blocks" of particle effects in the world.  All particles that are
supported by Bukkit can be used.  The density of particles is configurable for
each "group" of particles and a soft limit to the maximum number of blocks in
a group can be set in the config.

The hard limit (due to Java) is around `2^31 - 1`, but that is over two cubic
kilometers, so you probably won't hit it anyways.  The higher you set the
density, the more lag there's going to be.  But as of ~1.9, Mojang fixed a lot
of what was causing lag with particles so it's probably not going to be *that*
bad.  Particle types also have their own activation ranges that I can't
control, so even if you set an activation range in BlockParticles that's above
this range then you still won't be able to see them until you get closer.

The particles you can use is any of those supported by Bukkit.  A list of all
of the available particles is [here](https://hub.spigotmc.org/javadocs/bukkit/org/bukkit/Particle.html).

Groups don't have to be contiguous.

## Permissions

* **blockparticles.cmd.create** : Creation of partcle blocks.
* **blockparticles.type.[...]** : Gives the specific type of particle (for example, `VILLAGER_HAPPY` translates to `blockparticles.type.villagerhappy`).
* **blockparticles.cmd.settype** : Allows setting the type of particles for groups.
* **blockparticles.cmd.setdensity** : Allows setting the densities of particles.
* **blockparticles.cmd.setrange** : Allows setting the activation range of particles.

## Commands

* `/bpcreate <name> <type>` : Starts a new session, any blocks you place will turn into particleblocks.
* `/bpstop` : Stops placing particle blocks and ends your placement session.
* `/bpsettype <name> <type>` : Changes the type of particle for the group.
* `/bpsetdensity <name> <density>` : Changes the density of the group.
* `/bpsetrange <name> <range>` : Changes the visibility range of the group.

