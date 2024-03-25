from PIL import Image

# Ouvrir l'image d'origine
img = Image.open('Sprites/Run.png')

# Définir la largeur et la hauteur d'une sous-image
width, height = 200, 200

# Diviser l'image en sous-images et les enregistrer dans des fichiers séparés
for i in range(8):
    # Calculer les coordonnées de la sous-image
    left = i * width
    upper = 0
    right = left + width
    lower = 200

    # Découper la sous-image à partir de l'image d'origine
    sub_img = img.crop((left, upper, right, lower))

    # Enregistrer la sous-image dans un fichier séparé
    sub_img.save(f'../run/run_{i}.png')
