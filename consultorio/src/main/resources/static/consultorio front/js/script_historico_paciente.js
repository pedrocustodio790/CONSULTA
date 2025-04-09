document.addEventListener('DOMContentLoaded', function () {
    document.getElementById('buscar').addEventListener('click', async () => {
      const id = document.getElementById('pacienteId').value.trim();
      const tabela = document.getElementById('tabela-consultas');
      const resultado = document.getElementById('resultado');
      tabela.innerHTML = '';
      resultado.classList.add('d-none');
  
      if (!id) return alert('Digite um ID válido');
  
      try {
        const res = await fetch(`http://localhost:8080/consultas/paciente/${id}/resumo`);
        if (!res.ok) throw new Error('Paciente não encontrado ou sem histórico');
        const consultas = await res.json();
  
        if (consultas.length === 0) return alert('Nenhuma consulta encontrada');
  
        consultas.forEach(c => {
          const tr = document.createElement('tr');
          tr.innerHTML = `
            <td>${c.id}</td>
            <td>${new Date(c.dataHora).toLocaleString()}</td>
            <td>${c.motivo}</td>
            <td>${c.nomeMedico}</td>
            <td>${c.retorno ? 'Sim' : 'Não'}</td>
            <td>${c.atestado || ''}</td>
            <td>R$ ${c.valorConsulta?.toFixed(2) || '0,00'}</td>
          `;
          tabela.appendChild(tr);
        });
  
        resultado.classList.remove('d-none');
      } catch (err) {
        alert(err.message);
      }
    });
  });
  