export interface TypeTheme {
  bg: string
  border: string
  ring: string,
  optionPointed: string,
}

const THEMES: Record<string, TypeTheme> = {
  normal: { border: '!border-normal', bg: '!bg-normal', ring: '!ring-normal/30', optionPointed: '!bg-normal/10' },
  fighting: { border: '!border-fighting', bg: '!bg-fighting', ring: '!ring-fighting/30', optionPointed: '!bg-fighting/10' },
  flying: { border: '!border-flying', bg: '!bg-flying', ring: '!ring-flying/30', optionPointed: '!bg-flying/10' },
  poison: { border: '!border-poison', bg: '!bg-poison', ring: '!ring-poison/30', optionPointed: '!bg-poison/10' },
  ground: { border: '!border-ground', bg: '!bg-ground', ring: '!ring-ground/30', optionPointed: '!bg-ground/10' },
  rock: { border: '!border-rock', bg: '!bg-rock', ring: '!ring-rock/30', optionPointed: '!bg-rock/10' },
  bug: { border: '!border-bug', bg: '!bg-bug', ring: '!ring-bug/30', optionPointed: '!bg-bug/10' },
  ghost: { border: '!border-ghost', bg: '!bg-ghost', ring: '!ring-ghost/30', optionPointed: '!bg-ghost/10' },
  steel: { border: '!border-steel', bg: '!bg-steel', ring: '!ring-steel/30', optionPointed: '!bg-steel/10' },
  fire: { border: '!border-fire', bg: '!bg-fire', ring: '!ring-fire/30', optionPointed: '!bg-fire/10' },
  water: { border: '!border-water', bg: '!bg-water', ring: '!ring-water/30', optionPointed: '!bg-water/10' },
  grass: { border: '!border-grass', bg: '!bg-grass', ring: '!ring-grass/30', optionPointed: '!bg-grass/10' },
  electric: { border: '!border-electric', bg: '!bg-electric', ring: '!ring-electric/30', optionPointed: '!bg-electric/10' },
  psychic: { border: '!border-psychic', bg: '!bg-psychic', ring: '!ring-psychic/30', optionPointed: '!bg-psychic/10' },
  ice: { border: '!border-ice', bg: '!bg-ice', ring: '!ring-ice/30', optionPointed: '!bg-ice/10' },
  dragon: { border: '!border-dragon', bg: '!bg-dragon', ring: '!ring-dragon/30', optionPointed: '!bg-dragon/10' },
  dark: { border: '!border-dark', bg: '!bg-dark', ring: '!ring-dark/30', optionPointed: '!bg-dark/10' },
  fairy: { border: '!border-fairy', bg: '!bg-fairy', ring: '!ring-fairy/30', optionPointed: '!bg-fairy/10' },
}

const ID_MAP: Record<number, TypeTheme> = {
  1: THEMES.normal as TypeTheme,
  2: THEMES.fighting as TypeTheme,
  3: THEMES.flying as TypeTheme,
  4: THEMES.poison as TypeTheme,
  5: THEMES.ground as TypeTheme,
  6: THEMES.rock as TypeTheme,
  7: THEMES.bug as TypeTheme,
  8: THEMES.ghost as TypeTheme,
  9: THEMES.steel as TypeTheme,
  10: THEMES.fire as TypeTheme,
  11: THEMES.water as TypeTheme,
  12: THEMES.grass as TypeTheme,
  13: THEMES.electric as TypeTheme,
  14: THEMES.psychic as TypeTheme,
  15: THEMES.ice as TypeTheme,
  16: THEMES.dragon as TypeTheme,
  17: THEMES.dark as TypeTheme,
  18: THEMES.fairy as TypeTheme
}

export const getTypeTheme = (key?: string | number): TypeTheme => {
  if (typeof key === 'number') return ID_MAP[key] as TypeTheme || THEMES.normal
  return THEMES[key?.toLowerCase() || ''] as TypeTheme || THEMES.normal
}

export const getTypeHex = (id: number): string => {
  const hexMap: Record<number, string> = {
    1: '#9FA19F', // Normal
    2: '#FF8000', // Fighting
    3: '#81B9EF', // Flying
    4: '#9141CB', // Poison
    5: '#915121', // Ground
    6: '#AFA981', // Rock
    7: '#91A119', // Bug
    8: '#704170', // Ghost
    9: '#60A1B8', // Steel
    10: '#E62829', // Fire
    11: '#2980EF', // Water
    12: '#3FA129', // Grass
    13: '#FAC000', // Electric
    14: '#EF4179', // Psychic
    15: '#3DCEF3', // Ice
    16: '#5060E1', // Dragon
    17: '#624D4E', // Dark
    18: '#EF70EF', // Fairy
  }
  return hexMap[id] || '#10b981' // Default emerald
}