export interface StatBlockDto {
  hp: number
  attack: number
  defense: number
  specialAttack: number
  specialDefense: number
  speed: number
}

export type StatSortKey = keyof StatBlockDto
