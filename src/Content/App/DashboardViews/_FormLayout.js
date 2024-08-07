import React, { useState, useCallback } from 'react';
import { TextField, Box, Button, Autocomplete, Chip } from '@mui/material';
import { Add as AddIcon, Remove as RemoveIcon } from '@mui/icons-material';
import { styled } from '@mui/material/styles';

// Styled Chip component to handle text truncation
const StyledChip = styled(Chip)(({ theme }) => ({
  '& .MuiChip-label': {
    whiteSpace: 'nowrap',
    overflow: 'hidden',
    textOverflow: 'ellipsis',
    maxWidth: '90px' // Adjust this value to control the max width before ellipsis
  }
}));

// Helper function to split a comma-separated string into an array
const splitStringToArray = (str) => str ? str.split(',').map(item => item.trim()) : [];

// Function to render text fields based on the props
const FormLayout = ({
  nombre,
  nombreCompleto,
  cargo,
  email,
  telefono,
  medio,
  tipoMedio,
  tematica,
  redesSociales,
  clientes,
  pais,
  comunidad,
  region
}) => {

  // Initialize the state with emails parsed from the prop
  const [emails, setEmails] = useState(splitStringToArray(email));

  // Handle changes in email input fields
  const handleEmailChange = (index, value) => {
    const newEmails = [...emails];
    newEmails[index] = value;
    setEmails(newEmails);
  };

  // Add a new email field
  const handleAddField = () => {
    setEmails([...emails, '']);
  };

  // Remove an email field
  const handleRemoveField = (index) => {
    const newEmails = emails.filter((_, i) => i !== index);
    setEmails(newEmails);
  };


  return (
    <>
      <div style={{ width: "25%" }}>
        <TextField
          className='form-control'
          id="nombre"
          label="Nombre"
          variant="outlined"
          fullWidth
          defaultValue={nombre}
          margin='normal'
        />
        <TextField
          className='form-control'
          id="nombreCompleto"
          label="Nombre Completo"
          variant="outlined"
          fullWidth
          margin='normal'
          defaultValue={nombreCompleto}
        />
        {emails.map((email, index) => (
          <Box key={index} display='flex' alignItems='center'>
            <TextField
              className='form-control'
              id={`email-${index}`}
              label={`Email ${index + 1}`}
              variant="outlined"
              fullWidth
              value={email}
              onChange={(e) => handleEmailChange(index, e.target.value)}
            />
            {index > 0 && (
              <button
                onClick={() => handleRemoveField(index)}
                className='button-form'
              >
                <RemoveIcon />
              </button>
            )}
            {index === emails.length - 1 && (
              <button
                onClick={handleAddField}
                className='button-form'
              >
                <AddIcon />
              </button>
            )}
          </Box>
        ))}
      </div>
      <div style={{ width: "25%" }}>
        <Autocomplete
          className='form-control'
          multiple
          freeSolo
          limitTags={1}
          id="cargo"
          options={["The Shawshank Redemption", "The Godfather", "The Dark Knight"]}
          renderInput={(params) => (
            <TextField {...params} label="Cargo" variant="outlined" margin='nomal' />
          )}
          renderTags={(value, getTagProps) =>
            value.map((option, index) => (
              <StyledChip
                label={option}
                {...getTagProps({ index })}
                key={index}
              />
            ))
          }
          fullWidth
        />
        <Autocomplete
          className='form-control'
          multiple
          freeSolo
          limitTags={1}
          id="medio"
          options={["The Shawshank Redemption", "The Godfather", "The Dark Knight"]}
          renderInput={(params) => (
            <TextField {...params} label="Medio" variant="outlined" margin='nomal' />
          )}
          renderTags={(value, getTagProps) =>
            value.map((option, index) => (
              <StyledChip
                label={option}
                {...getTagProps({ index })}
                key={index}
              />
            ))
          }
          fullWidth
        />
        <Autocomplete
          className='form-control'
          multiple
          freeSolo
          limitTags={1}
          id="tipoMedio"
          options={["The Shawshank Redemption", "The Godfather", "The Dark Knight"]}
          renderInput={(params) => (
            <TextField {...params} label="Tipo de Medio" variant="outlined" margin='nomal' />
          )}
          renderTags={(value, getTagProps) =>
            value.map((option, index) => (
              <StyledChip
                label={option}
                {...getTagProps({ index })}
                key={index}
              />
            ))
          }
          fullWidth
        />
        <Autocomplete
          className='form-control'
          multiple
          freeSolo
          limitTags={1}
          id="tematica"
          options={["The Shawshank Redemption", "The Godfather", "The Dark Knight"]}
          renderInput={(params) => (
            <TextField {...params} label="Temática" variant="outlined" margin='nomal' />
          )}
          renderTags={(value, getTagProps) =>
            value.map((option, index) => (
              <StyledChip
                label={option}
                {...getTagProps({ index })}
                key={index}
              />
            ))
          }
          fullWidth
        />
      </div>
      <div style={{ width: "25%" }}>
        <Autocomplete
          className='form-control'
          freeSolo
          limitTags={1}
          id="pais"
          options={["The Shawshank Redemption", "The Godfather", "The Dark Knight"]}
          renderInput={(params) => (
            <TextField {...params} label="País" variant="outlined" margin='nomal' />
          )}
          renderTags={(value, getTagProps) =>
            value.map((option, index) => (
              <StyledChip
                label={option}
                {...getTagProps({ index })}
                key={index}
              />
            ))
          }
          fullWidth
        />
        <Autocomplete
          className='form-control'
          freeSolo
          limitTags={1}
          id="comunidad"
          options={["The Shawshank Redemption", "The Godfather", "The Dark Knight"]}
          renderInput={(params) => (
            <TextField {...params} label="Comunidad" variant="outlined" margin='nomal' />
          )}
          renderTags={(value, getTagProps) =>
            value.map((option, index) => (
              <StyledChip
                label={option}
                {...getTagProps({ index })}
                key={index}
              />
            ))
          }
          fullWidth
        />
        <Autocomplete
          className='form-control'
          multiple
          freeSolo
          limitTags={1}
          id="region"
          options={["The Shawshank Redemption", "The Godfather", "The Dark Knight"]}
          renderInput={(params) => (
            <TextField {...params} label="Región" variant="outlined" margin='nomal' />
          )}
          renderTags={(value, getTagProps) =>
            value.map((option, index) => (
              <StyledChip
                label={option}
                {...getTagProps({ index })}
                key={index}
              />
            ))
          }
          fullWidth
        />
        <TextField
          className='form-control'
          id="localizacionPersonal"
          label="Localización Personal"
          variant="outlined"
          fullWidth
          margin='normal'
        />
        <TextField
          className='form-control'
          id="localizacionProfesional"
          label="Localización Profesional"
          variant="outlined"
          fullWidth
          margin='normal'
        />
      </div>
      <div style={{ width: "25%" }}>
        <TextField
          className='form-control'
          multiline
          rows={10}
          sx={{ height: '270px' }}
          id="observaciones"
          label="Observaciones"
          variant="outlined"
          fullWidth
          margin='normal'
        />
        <TextField
          className='form-control'
          id="cargo4"
          label="Cargo"
          variant="outlined"
          margin='normal'
          fullWidth
          defaultValue={cargo}
        />
      </div>
    </>
  );
};

export default FormLayout;
