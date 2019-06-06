<?php

namespace SFL2\ApplicationBundle\Form;

use SFL2\ApplicationBundle\Repository\UtilisateurRepository;
use SFL2\ApplicationBundle\Entity\Utilisateur;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\TextType;
use Symfony\Component\Form\Extension\Core\Type\EmailType;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;
use Symfony\Component\Form\Extension\Core\Type\PasswordType;

class UtilisateurType extends AbstractType {

    /**
     * {@inheritdoc}
     */
    public function buildForm(FormBuilderInterface $builder, array $options) {
        $builder->add('nom', TextType::class, array('label' => 'Nom', 'required' => true))
                ->add('prenom', TextType::class, array('label' => 'Prénom', 'required' => true))
                ->add('fonction', TextType::class, array('label' => 'Numéro', 'required' => true))
                ->add('login', TextType::class, array('label' => 'Identifiant', 'required' => true))
                ->add('motdepasse', PasswordType::class, array('label' => 'Mot de passe', 'required' => true))
                ->add('commentaires', TextType::class,array('label' => 'Commentaire', 'required' => false))
                ->add('ajouter', SubmitType::class, array('label' => 'Enregistrer', 'attr' => array('class' => 'btn btn-block btn-lg btn-success')));
        
    }

    /**
     * {@inheritdoc}
     */
    public function configureOptions(OptionsResolver $resolver) {
        $resolver->setDefaults(array(
            'data_class' => Utilisateur::class
        ));
    }

    /**
     * {@inheritdoc}
     */
    public function getBlockPrefix() {
        return 'sfl2_utilisateur';
    }
    


}
