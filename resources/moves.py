import json
import csv
from tqdm import tqdm
from requests import Session

capa_csv = []
with open("listeCapacites.csv", "r", encoding="UTF8") as f:
    for row in csv.reader(f, delimiter=";"):
        capa_csv.append(row[0].strip("*")) # Bombe Œuf a une etoile a la fin

capa_csv.pop(0)


with open("pok_gen1.json", "r", encoding="UTF8") as f:
    pokemons = json.load(f)["pokemon_species"]

saved_moves = []
with Session() as sess:
    with open("out.txt", "w", encoding="UTF8") as f:
        for pok in tqdm(pokemons):
            pok_full = sess.get(pok["url"]).json() # https://pokeapi.co/docs/v2#pokemon-species
    
            # on trouve le nom en fr
            for name in pok_full["names"]:
                    if name["language"]["name"] == "fr":
                        pok_fr = name["name"]

            f.write(f"{pok_fr} : ")

            varieties = pok_full["varieties"]
            for var in varieties:
                if var["is_default"]:
                    url = var["pokemon"]["url"]
            
            # NON
            moves = sess.get(url).json()["moves"] # https://pokeapi.co/docs/v2#pokemon
            for mv in moves:
                full_mv = sess.get(mv["move"]["url"]).json()
                
                # est-ce que c'est une attaque de la generation 1 ?
                if full_mv["generation"]["name"] != "generation-i":
                    continue
                
                # physique ou special
                if full_mv["damage_class"] == "status":
                    continue

                # encore une fois on trouve le nom fr
                for name in full_mv["names"]:
                    if name["language"]["name"] == "fr":
                        nom_fr = name["name"]
                        
                # est-ce que cette capacité est présente dans le fichier ?
                if nom_fr not in capa_csv:
                    continue
                
                saved_moves.append(nom_fr)
                f.write(f"{nom_fr}, ")
            f.write("\n")

        saved_moves = set(saved_moves)
        f.write(f"\n{len(saved_moves)} total moves :\n{saved_moves}")












