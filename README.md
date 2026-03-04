# 🛡️ PokéCounter
A high-performance Pokémon counter dashboard built with Vue 3, Vite, and Supabase. This tool allows you to find "Perfect Walls" and "Strong Alternatives" against specific Pokémon threats, featuring real-time stat sorting and smart links to Bulbapedia.

## 🚀 Features
Smart Tiering: Automatically groups Pokémon into "Perfect Walls" and "Strong Alternatives."

Dynamic Sorting: Re-order results within tiers by their stats.

Collapsible Tiers: Clean UX for managing large sets of counter-data.

Auto-Sync: Custom script to pull the latest data (including forms and species) from PokéAPI into your own Supabase instance.

Smart Linking: Deep-links directly to Bulbapedia species pages.

## 🛠️ Setup
### 1. Clone & Install
```
git clone https://github.com/yourusername/pokecounter.git
cd pokecounter
npm install
```
### 2. Environment Variables
Create a .env file in the root directory. Do not commit this file.

```
# Supabase Configuration
VITE_SUPABASE_URL=your_supabase_project_url
VITE_SUPABASE_ANON_KEY=your_supabase_anon_key

# PokéAPI Configuration
POKEAPI_BASE_URL=https://pokeapi.co/api/v2
```
### 3. Database Schema
The Schema can be found inside supabase.sql

## 🔄 Syncing Data
To populate your database from PokéAPI, run the sync script. This script handles rate-limiting and batches requests for efficiency.

```
npm run db:sync
```
## 💻 Development
```
# Start the dev server
npm run dev

# Build for production
npm run build
```
## 💡 Contributing
Found a bug with a specific Pokémon's Bulbapedia link or stat calculation?

Open an Issue.

Submit a Pull Request.

## 🔒 Security Note
This project uses .env files to manage database connectivity. Ensure .env is listed in your .gitignore to prevent leaking your Supabase credentials.