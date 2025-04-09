const API_BASE = 'http://localhost:8080';

document.addEventListener('DOMContentLoaded', () => {
  const form = document.getElementById('paciente-form');
  const filtroBtn = document.getElementById('filtrar-pacientes');

  form.addEventListener('submit', async event => {
    event.preventDefault();

    const paciente = {
      nome: form.nomePaciente.value.trim(),
      cpf: form.cpf.value.trim(),
      telefone: form.telefonePaciente.value.trim(),
      dataNascimento: form.dataNascimento.value,
      endereco: form.endereco.value.trim(),
      planoSaude: form.planoSaude.value.trim()
    };
    console.log("Enviando paciente:", paciente);

    try {
      const response = await fetch(`${API_BASE}/pacientes`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(paciente)
      });

      if (!response.ok) throw new Error('Erro ao cadastrar paciente');

      alert('Paciente cadastrado com sucesso!');
      form.reset();
      carregarPacientes();
    } catch (error) {
      alert(error.message);
    }
  });

  filtroBtn.addEventListener('click', carregarPacientes);
  carregarPacientes();
});

async function carregarPacientes() {
  try {
    const response = await fetch('http://localhost:8080/pacientes');
    if (!response.ok) throw new Error('Erro ao obter pacientes');

    const pacientes = await response.json();
    const filtro = document.getElementById('filtroNomePaciente').value.toLowerCase();
    const tbody = document.querySelector('#pacientes-table tbody');
    tbody.innerHTML = '';

    pacientes
      .filter(p => p.nome.toLowerCase().includes(filtro))
      .forEach(paciente => {
        const tr = document.createElement('tr');
        tr.appendChild(criarTd(paciente.id));
        tr.appendChild(criarTd(paciente.nome));
        tr.appendChild(criarTd(paciente.cpf));
        tr.appendChild(criarTd(paciente.telefone || ''));
        tr.appendChild(criarTd(paciente.dataNascimento || ''));
        tr.appendChild(criarTd(paciente.endereco || ''));
        tr.appendChild(criarTd(paciente.planoSaude || ''));
        tbody.appendChild(tr);
      });
  } catch (error) {
    alert(error.message);
  }
}

function criarTd(conteudo) {
  const td = document.createElement('td');
  td.textContent = conteudo;
  return td;
}
