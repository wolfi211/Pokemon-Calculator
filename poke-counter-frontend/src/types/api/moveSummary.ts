import type { MinifiedTypeDto } from './minifiedType'

export interface MinifiedDamageClassDto {
  id: number
  name: string
  localizedName: string
}

export interface MinifiedMoveSearchResponse {
  id: number
  name: string
  types: MinifiedTypeDto[]
  damageClass: MinifiedDamageClassDto | null
  localizedName: string
}
