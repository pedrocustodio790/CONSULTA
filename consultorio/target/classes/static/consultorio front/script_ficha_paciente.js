document.addEventListener('DOMContentLoaded', function () {
    document.getElementById('buscarFicha').addEventListener('click', async () => {
      const id = document.getElementById('pacienteId').value.trim();
      const fichaURL = `http://localhost:8080/pacientes/${id}/ficha`;
  
      if (!id) return alert("Digite um ID válido.");
  
      try {
        const res = await fetch(fichaURL);
        if (!res.ok) throw new Error("Paciente não encontrado.");
        const ficha = await res.json();
  
        const paciente = ficha.paciente;
        const consultas = ficha.consultas;
  
        document.getElementById('nome').textContent = paciente.nome;
        document.getElementById('cpf').textContent = paciente.cpf;
        document.getElementById('telefone').textContent = paciente.telefone;
        document.getElementById('dataNascimento').textContent = paciente.dataNascimento;
        document.getElementById('endereco').textContent = paciente.endereco;
        document.getElementById('planoSaude').textContent = paciente.planoSaude;
  
        const tabela = document.getElementById('tabelaConsultas');
        tabela.innerHTML = '';
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
  
        document.getElementById('dadosPaciente').classList.remove('d-none');
        document.getElementById('consultasPaciente').classList.remove('d-none');
      } catch (error) {
        alert(error.message);
      }
    });
  });
  