<?php

namespace SFL2\ApplicationBundle\Form;

use SFL2\ApplicationBundle\Repository\UtilisateurRepository;
use SFL2\ApplicationBundle\Entity\Utilisateur;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\TextType;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;
use Symfony\Component\Form\Extension\Core\Type\PasswordType;

class ConnexionType extends AbstractType {

    /**
     * {@inheritdoc}
     */
    public function buildForm(FormBuilderInterface $builder, array $options) {
        $builder->add('login', TextType::class, array('label' => 'login', 'required' => true))
                ->add('motdepasse', PasswordType::class, array('label' => 'Password', 'required' => true))
                ->add('connexion', SubmitType::class, array('label' => 'connexion', 'attr' => array('class' => 'btn btn-block btn-lg btn-success')));
        
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
        return 'sfl2_homepage';
    }
    


}
