<!DOCTYPE html>
<html>
<head>
  <style>
    table {
      border-collapse: collapse;
      width: 100%;
    }
    th, td {
      border: 1px solid #ddd;
      padding: 8px;
    }
    tr:nth-child(even) {
      background-color: #f2f2f2;
    }
    th {
      background-color: #198754;
      color: white;
    }
  </style>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/pdf.js/2.11.338/pdf.min.js"></script>

</head>
<body>
<nav class="navbar navbar-expand-sm navbar-dark bg-dark">
  <div class="container-fluid">
    <a class="navbar-brand" href="javascript:void(0)">Logo</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#mynavbar">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="mynavbar">
      <ul class="navbar-nav me-auto">
        <li class="nav-item">
          <a class="nav-link" href="javascript:void(0)">Cerca dipendente per i dati forniti nella tabella (Nome, Cognome, Stipendio, Sesso, Impiego)</a>
        </li>
        
      </ul>
      <form class="d-flex">
        <input class="form-control me-2" name="search" id="search" type="text" placeholder="Search">
        <button class="btn btn-success" value="Cerca" type="button">Cerca</button>
      </form>
    </div>
  </div>
</nav>

  <?php
      // Connessione al database MySQL
      $conn = mysqli_connect('localhost', 'root', '', 'sqd');
      
      if (!$conn) {
        die('Connessione al database fallita: ' . mysqli_connect_error());
      }
      ?>
<div class="container-fluid p-5 bg-success text-white text-center">
 <h2>Aggiungi Dipendente</h2>
  <form method="POST" action="aggiungi.php">
    <label for="nome">Nome:</label>
    <input type="text" id="nome" name="Nome" required>
    <label for="cognome">Cognome:</label>
    <input type="text" id="cognome" name="Cognome" required>
    <label for="sesso">Sesso:</label>
    <input type="text" id="sesso" name="Sesso" required>
    <label for="nascita">Nascita:</label>
    <input type="text" id="nascita" name="Nascita" required>
    <label for="stipendio">Stipendio:</label>
    <input type="text" id="stipendio" name="Stipendio" required>
    <label for="impiego">Impiego:</label>
    <input type="text" id="impiego" name="Impiego" required>
    <input type="submit" value="Aggiungi">
  </form>
</div>
<div class="mt-4 p-5 bg-dark text-white ">
  

  <form method="POST" action="">
        <button type="submit" class="btn btn-success" name="calcola">Calcola</button>
  </form>
  <?php if (isset($_POST['calcola'])) {
        // Connessione al database

        // Query per calcolare la media e la somma degli stipendi
        $sql = "SELECT AVG(Stipendio) AS media, SUM(Stipendio) AS somma FROM dipendenti";
        $result = $conn->query($sql);

        if ($result->num_rows > 0) {
            $row = $result->fetch_assoc();
            $media = $row['media'];
            $somma = $row['somma'];

            echo "<h2>Risultati:</h2>";
            echo "Media stipendi: " . $media . "<br>";
            echo "Somma stipendi: " . $somma . "<br>";
        } else {
            echo "Nessun dipendente trovato.";
        }
    }
    ?>

  <form method="POST" action="">
        <button type="submit" class="btn btn-success" name="stampalista">Stampa Lista</button>
    </form>
    <?php
    if (isset($_POST['stampalista'])) {
      // Connessione al database
    
      if ($conn->connect_error) {
          die("Connessione fallita: " . $conn->connect_error);
      }
    
      // Query per recuperare la lista dei dipendenti
      $sql = "SELECT * FROM dipendenti";
      $result = $conn->query($sql);
    
      if ($result->num_rows > 0) {
          echo "<h2>Elenco Dipendenti:</h2>";
          echo "<ul>";
          while ($row = $result->fetch_assoc()) {
              echo "<li>" . $row['Nome'] . " " . $row['Cognome'] . "</li>";
          }
          echo "</ul>";
      } else {
          echo "Nessun dipendente trovato.";
      }

     
    }?>

<form method="POST" enctype="multipart/form-data" action="">
        <button type="submit" class="btn btn-success" name="visualizza">Visualizza</button>
    </form>

    <?php
    if (isset($_POST['visualizza'])) {
            // Percorso del file di testo
    $file = 'C:\Users\User\OneDrive\Desktop\dipendenti.txt';

    // Leggi il contenuto del file
    $content = file_get_contents($file);

    // Mostra il contenuto del file nell'HTML
    echo "<pre>" . htmlspecialchars($content) . "</pre>";
        mysqli_close($conn);
    }
    ?>
    
<button class="btn btn-success" onclick="apriPDF()">Apri PDF</button>
  <canvas id="pdfCanvas"></canvas>
<br>
<br>
<br>
<form action="logout.php" method="post">
    <input type="submit" class="btn btn-success" value="Logout">
  </form>
</div>
  <h2>Tabella Dipendenti</h2>
  <table>
    <tr>
      <th>id</th>
      <th>Nome</th>
      <th>Cognome</th>
      <th>Sesso</th>
      <th>Nascita</th>
      <th>Stipendio</th>
      <th>Impiego</th>
      <th>Orario di Inserimento</th>
      <th>Azioni</th>
    </tr>   
    <?php
      // Connessione al database MySQL
      $conn = mysqli_connect('localhost', 'root', '', 'sqd');
      
      if (!$conn) {
        die('Connessione al database fallita: ' . mysqli_connect_error());
      }

      if (isset($_GET['search'])) {
        $search = $_GET['search'];
        $query = "SELECT * FROM dipendenti WHERE CONCAT(Nome, ' ', Cognome, ' ', Sesso, ' ', Nascita, ' ', Stipendio, ' ', Impiego) LIKE '%$search%'";
      } else {
        $query = "SELECT * FROM dipendenti";
      }
      
    $result = $conn->query($query);

    if ($result->num_rows > 0) {
    

      while ($row = $result->fetch_assoc()) {
        echo '<tr>';
        echo '<td>' . $row['id'] . '</td>';
        echo '<td>' . $row['Nome'] . '</td>';
        echo '<td>' . $row['Cognome'] . '</td>';
        echo '<td>' . $row['Sesso'] . '</td>';
        echo '<td>' . $row['Nascita'] . '</td>';
        echo '<td>' . $row['Stipendio'] . '</td>';
        echo '<td>' . $row['Impiego'] . '</td>';
        echo '<td>' . $row['OrarioInserimento'] . '</td>';
        echo "<td>";
        echo "<button onclick=\"modificaDipendente(" . $row['id'] . ")\">Modifica</button>";
        echo "<button onclick=\"eliminaDipendente(" . $row['id'] . ")\">Elimina</button>";
        echo "</td>";
      }

      echo '</table>';
    } else {
      echo 'Nessun dato trovato.';
    }

    $query = "SELECT * FROM dipendenti";
$result = $conn->query($query);



      // Esegui la query per ottenere i dati dei dipendenti
      $query = "SELECT * FROM dipendenti";
      $result = mysqli_query($conn, $query);

 
    ?>
  </table>

  
 
  <script>
    
    function modificaDipendente(id) {
      window.location.href = "modifica.php?id=" + id;
    }

    function eliminaDipendente(id) {
      if (confirm("Sei sicuro di voler eliminare questo dipendente?")) {
        window.location.href = "elimina.php?id=" + id;
        
      }
    }

    function apriPDF() {
      var pdfUrl = 'PDF.pdf';
      
      // Carica il PDF utilizzando PDF.js
      pdfjsLib.getDocument(pdfUrl).promise.then(function(pdf) {
        // Ottieni la prima pagina del PDF
        pdf.getPage(1).then(function(page) {
          var canvas = document.getElementById('pdfCanvas');
          var context = canvas.getContext('2d');
          
          // Imposta le dimensioni del canvas
          var viewport = page.getViewport({ scale: 1 });
          canvas.width = viewport.width;
          canvas.height = viewport.height;
          
          // Disegna la pagina del PDF nel canvas
          page.render({
            canvasContext: context,
            viewport: viewport
          });
        });
      });
    }

  </script>
</body>
</html>