package thaumicbees.bees;

import forestry.api.apiculture.*;
import forestry.api.genetics.IAllele;
import forestry.api.genetics.IGenome;
import java.util.ArrayList;

import cpw.mods.fml.common.FMLLog;

import thaumcraft.api.AuraNode;
import thaumcraft.api.EnumNodeType;
import thaumcraft.api.ThaumcraftApi;
import thaumicbees.main.ThaumicBees;
import thaumicbees.main.utils.MoonPhase;

public class BeeMutation implements IBeeMutation
{
	public static BeeMutation Esoteric;
	public static BeeMutation Esoteric1;
	public static BeeMutation Mysterious;
	public static BeeMutation Mysterious1;
	public static BeeMutation Arcane;
	public static BeeMutation Charmed;
	public static BeeMutation Enchanted;
	public static BeeMutation Supernatural;
	public static BeeMutation Stark;
	public static BeeMutation Pupil;
	public static BeeMutation Scholarly;
	public static BeeMutation Savant;
	public static BeeMutation Aware;
	public static BeeMutation Vis;
	public static BeeMutation Vis1;
	public static BeeMutation Pure;
	public static BeeMutation Flux;
	public static BeeMutation Node;
	public static BeeMutation Node1;
	public static BeeMutation Skulking;
	public static BeeMutation Brainy;
	public static BeeMutation Gossamer;
	public static BeeMutation Wispy;
	public static BeeMutation Batty;
	public static BeeMutation Ghastly;
	public static BeeMutation Timely;
	public static BeeMutation Lordly;
	public static BeeMutation Doctoral;
	
	public static void setupMutations()
	{
		BeeMutation.Esoteric = new BeeMutation(Allele.getBaseSpecies("Imperial"), Allele.getBaseSpecies("Demonic"), BeeSpecies.ESOTERIC, 10);
		BeeMutation.Esoteric1 = new BeeMutation(Allele.getBaseSpecies("Heroic"), Allele.getBaseSpecies("Demonic"), BeeSpecies.ESOTERIC, 25);
		BeeMutation.Mysterious = new BeeMutation(BeeSpecies.ESOTERIC, Allele.getBaseSpecies("Ended"), BeeSpecies.MYSTERIOUS, 10);
		BeeMutation.Arcane = new BeeMutation(BeeSpecies.ESOTERIC, BeeSpecies.MYSTERIOUS, BeeSpecies.ARCANE, 8)
			.setMoonPhaseBonus(MoonPhase.WANING_CRESCENT, MoonPhase.WAXING_CRESCENT, 1.5f);
		BeeMutation.Charmed = new BeeMutation(Allele.getBaseSpecies("Diligent"), Allele.getBaseSpecies("Valiant"), BeeSpecies.CHARMED, 20);
		BeeMutation.Enchanted = new BeeMutation(BeeSpecies.CHARMED, Allele.getBaseSpecies("Valiant"), BeeSpecies.ENCHANTED, 8);
		BeeMutation.Supernatural = new BeeMutation(BeeSpecies.CHARMED, BeeSpecies.ENCHANTED, BeeSpecies.SUPERNATURAL, 10)
			.setMoonPhaseBonus(MoonPhase.WAXING_GIBBOUS, MoonPhase.WANING_GIBBOUS, 1.5f);
		BeeMutation.Pupil = new BeeMutation(BeeSpecies.ARCANE, BeeSpecies.ENCHANTED, BeeSpecies.PUPIL, 10);
		BeeMutation.Scholarly = new BeeMutation(BeeSpecies.PUPIL, BeeSpecies.ARCANE, BeeSpecies.SCHOLARLY, 8);
		BeeMutation.Savant = new BeeMutation(BeeSpecies.PUPIL, BeeSpecies.SCHOLARLY, BeeSpecies.SAVANT, 6);
		BeeMutation.Stark = new BeeMutation(BeeSpecies.ARCANE, BeeSpecies.SUPERNATURAL, BeeSpecies.STARK, 8);
		
		BeeMutation.Aware = new BeeMutation(Allele.getBaseSpecies("Demonic"), Allele.getBaseSpecies("Edenic"), BeeSpecies.AWARE, 12);
		BeeMutation.Vis = new BeeMutation(BeeSpecies.AWARE, BeeSpecies.ARCANE, BeeSpecies.VIS, 9)
		.setAuraNodeRequired(40);
		BeeMutation.Vis1 = new BeeMutation(BeeSpecies.AWARE, BeeSpecies.STARK, BeeSpecies.VIS, 12)
		.setAuraNodeRequired(80);
		
		BeeMutation.Pure = new BeeMutation(BeeSpecies.VIS, Allele.getBaseSpecies("Edenic"), BeeSpecies.PURE, 7)
			.setAuraNodeTypeRequired(5, EnumNodeType.PURE).setMoonPhaseBonus(MoonPhase.NEW, MoonPhase.NEW, 1.6f);
		BeeMutation.Flux = new BeeMutation(BeeSpecies.VIS, Allele.getBaseSpecies("Edenic"), BeeSpecies.FLUX, 7)
			.setAuraNodeTypeRequired(30, EnumNodeType.UNSTABLE).setMoonPhaseBonus(MoonPhase.FULL, MoonPhase.FULL, 1.6f);
				
		BeeMutation.Node = new BeeMutation(BeeSpecies.VIS, BeeSpecies.VIS, BeeSpecies.NODE, 2)
			.setAuraNodeRequired(4).setMoonPhaseRestricted(MoonPhase.WANING_HALF, MoonPhase.WANING_HALF);
		BeeMutation.Node1 = new BeeMutation(BeeSpecies.VIS, BeeSpecies.VIS, BeeSpecies.NODE, 2)
			.setAuraNodeRequired(4).setMoonPhaseRestricted(MoonPhase.WAXING_HALF, MoonPhase.WAXING_HALF);
		
		// Now we get into a little bit of branching...
		if (ThaumicBees.getConfig().ExtraBeesInstalled)
		{
			setupMutationsExtraBeesBase();
		}
		else
		{
			setupMutationsForestryBase();
		}
		BeeMutation.Gossamer.setMoonPhaseRestricted(MoonPhase.FULL, MoonPhase.WANING_CRESCENT);
		BeeMutation.Wispy = new BeeMutation(BeeSpecies.GOSSAMER, Allele.getBaseSpecies("Cultivated"), BeeSpecies.WISPY, 8);
		
		BeeMutation.Timely = new BeeMutation(Allele.getBaseSpecies("Industrious"), BeeSpecies.ENCHANTED, BeeSpecies.TIMELY, 10);
		BeeMutation.Lordly = new BeeMutation(BeeSpecies.TIMELY, Allele.getBaseSpecies("Imperial"), BeeSpecies.LORDLY, 9);
		BeeMutation.Doctoral = new BeeMutation(BeeSpecies.TIMELY, BeeSpecies.LORDLY, BeeSpecies.DOCTORAL, 7);
	}
	
	private static void setupMutationsExtraBeesBase()
	{
		try
		{
			BeeMutation.Skulking = new BeeMutation(BeeSpecies.MYSTERIOUS, Allele.getExtraSpecies("desolate"), BeeSpecies.SKULKING, 10);
			BeeMutation.Brainy = new BeeMutation(BeeSpecies.SKULKING, Allele.getExtraSpecies("rotten"), BeeSpecies.BRAINY, 12);
			BeeMutation.Gossamer = new BeeMutation(BeeSpecies.SKULKING, Allele.getExtraSpecies("ancient"), BeeSpecies.GOSSAMER, 10);
			BeeMutation.Batty = new BeeMutation(BeeSpecies.SKULKING, Allele.getExtraSpecies("rock"), BeeSpecies.BATTY, 14);
			BeeMutation.Ghastly = new BeeMutation(BeeSpecies.SKULKING, Allele.getExtraSpecies("creeper"), BeeSpecies.GHASTLY, 13);
		}
		catch (Exception e)
		{
			FMLLog.warning("ThaumicBees encountered an error attempting to register mutations using Extra Bees species! Ensure Extra Bees was able to initialize correctly!");
			FMLLog.info("Could not use Extra Bees species for mutations. Falling back to Forestry defaults.");
			setupMutationsForestryBase();
		}
	}
	
	private static void setupMutationsForestryBase()
	{
		BeeMutation.Skulking = new BeeMutation(BeeSpecies.MYSTERIOUS, Allele.getBaseSpecies("Modest"), BeeSpecies.SKULKING, 10);
		BeeMutation.Brainy = new BeeMutation(BeeSpecies.SKULKING, Allele.getBaseSpecies("Sinister"), BeeSpecies.BRAINY, 10);
		BeeMutation.Gossamer = new BeeMutation(BeeSpecies.SKULKING, BeeSpecies.SUPERNATURAL, BeeSpecies.GOSSAMER, 10);
		BeeMutation.Batty = new BeeMutation(BeeSpecies.SKULKING, Allele.getBaseSpecies("Frugal"), BeeSpecies.BATTY, 11);
		BeeMutation.Ghastly = new BeeMutation(BeeSpecies.SKULKING, Allele.getBaseSpecies("Austere"), BeeSpecies.GHASTLY, 13);
	}
	
	private IAllele parent1;
	private IAllele parent2;
	private IAllele mutationTemplate[];
	private int baseChance;
	private boolean isSecret;
	private boolean isMoonRestricted;
	private MoonPhase moonPhaseStart;
	private MoonPhase moonPhaseEnd;
	private float moonPhaseMutationBonus;
	private boolean nodeRequired;
	private EnumNodeType nodeType;
	private double nodeRange;

	public BeeMutation(IAlleleBeeSpecies species0, IAlleleBeeSpecies species1, BeeSpecies resultSpecies, int percentChance)
	{
		this.parent1 = species0;
		this.parent2 = species1;
		this.mutationTemplate = resultSpecies.getGenome();
		this.baseChance = percentChance;
		this.isSecret = false;
		this.isMoonRestricted = false;
		this.moonPhaseMutationBonus = -1f;
		this.nodeType = null;
		
		BeeManager.breedingManager.registerBeeMutation(this);
	}

	public int getChance(IBeeHousing housing, IAllele allele0, IAllele allele1, IGenome genome0, IGenome genome1)
	{
		int finalChance = 0;
		float chance = this.baseChance * 1f;
		
		if (this.arePartners(allele0, allele1))
		{
			// This mutation applies. Continue calculation.
			if (this.moonPhaseStart != null && this.moonPhaseEnd != null)
			{
				// Only occurs during the phases.
				if (this.isMoonRestricted && !MoonPhase.getMoonPhase(housing.getWorld()).isBetween(this.moonPhaseStart, this.moonPhaseEnd))
				{
					chance = 0;
				}
				else if (this.moonPhaseMutationBonus != -1f)
				{
					// There is a bonus to this mutation during moon phases...
					if (MoonPhase.getMoonPhase(housing.getWorld()).isBetween(this.moonPhaseStart, this.moonPhaseEnd))
					{
						chance = (int)(chance * this.moonPhaseMutationBonus);
					}
				}
			}
			
			if (this.nodeRequired)
			{
				int nodeId = ThaumcraftApi.getClosestAuraWithinRange(housing.getWorld(),
						housing.getXCoord(), housing.getYCoord(), housing.getZCoord(), this.nodeRange);
				if (nodeId >= 0)
				{
					if (this.nodeType != null)
					{
						// Needs a _specific_ variety of node
						AuraNode node = ThaumcraftApi.getNodeCopy(nodeId);
						if (node.type != this.nodeType)
						{
							chance = 0;
						}
					}
				}
				else
				{
					chance = 0;
				}
			}
			
			finalChance = Math.round(chance
					* housing.getMutationModifier((IBeeGenome) genome0,
							(IBeeGenome) genome1)
					* BeeManager.breedingManager.getBeekeepingMode(housing.getWorld())
							.getMutationModifier((IBeeGenome) genome0,
									(IBeeGenome) genome1));
		}
		
		return finalChance;
	}

	public IAllele getAllele0()
	{
		return parent1;
	}

	public IAllele getAllele1()
	{
		return parent2;
	}

	public IAllele[] getTemplate()
	{
		return mutationTemplate;
	}

	public int getBaseChance()
	{
		return baseChance;
	}

	public boolean isPartner(IAllele allele)
	{
		return parent1.getUID().equals(allele.getUID()) || parent2.getUID().equals(allele.getUID());
	}

	public IAllele getPartner(IAllele allele)
	{
		IAllele val = parent1;
		if (val.getUID().equals(allele.getUID()))
			val = parent2;
		return val;
	}
	
	public boolean arePartners(IAllele alleleA, IAllele alleleB)
	{
		return (this.parent1.getUID().equals(alleleA.getUID())) && this.parent2.getUID().equals(alleleB.getUID()) ||
				this.parent1.getUID().equals(alleleB.getUID()) && this.parent2.getUID().equals(alleleA.getUID());
	}
	
	public BeeMutation setSecret()
	{
		this.isSecret = true;
		
		return this;
	}

	public boolean isSecret()
	{
		return isSecret;
	}
	
	public BeeMutation setMoonPhaseRestricted(MoonPhase begin, MoonPhase end)
	{
		this.isMoonRestricted = true;
		this.moonPhaseStart = begin;
		this.moonPhaseEnd = end;
		
		return this;
	}
	
	public BeeMutation setMoonPhaseBonus(MoonPhase begin, MoonPhase end, float mutationBonus)
	{
		this.moonPhaseMutationBonus = mutationBonus;
		this.moonPhaseStart = begin;
		this.moonPhaseEnd = end;
		
		return this;
	}
	
	public BeeMutation setAuraNodeRequired(double range)
	{
		this.nodeRequired = true;
		this.nodeRange = range;
		
		return this;
	}
	
	public BeeMutation setAuraNodeTypeRequired(double range, EnumNodeType type)
	{
		this.nodeType = type;
		
		return this.setAuraNodeRequired(range);
	}
}